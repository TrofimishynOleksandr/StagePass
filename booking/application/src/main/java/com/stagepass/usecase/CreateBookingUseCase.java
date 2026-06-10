package com.stagepass.usecase;

import com.stagepass.command.CreateBookingCommand;
import com.stagepass.exception.TicketAlreadyReservedException;
import com.stagepass.exception.TicketAlreadySoldException;
import com.stagepass.model.Booking;
import com.stagepass.port.BookingRepository;
import com.stagepass.port.TicketDetails;
import com.stagepass.port.TicketProvider;
import com.stagepass.port.TicketReservation;
import com.stagepass.shared.domain.DomainEventPublisher;
import com.stagepass.shared.identity.BookingId;
import com.stagepass.shared.model.TicketStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateBookingUseCase {
    private final BookingRepository bookingRepository;
    private final DomainEventPublisher publisher;
    private final TicketProvider ticketProvider;
    private final TicketReservation ticketReservation;

    public BookingId execute(CreateBookingCommand command) {
        TicketDetails ticket = ticketProvider.getTicketDetails(command.ticketId());

        if (ticket.status() == TicketStatus.SOLD) {
            throw new TicketAlreadySoldException(command.ticketId());
        }

        boolean reserved = ticketReservation.reserve(
                ticket.eventId(),
                ticket.ticketId(),
                command.userId(),
                command.reservationTtl());

        if (!reserved) {
            throw new TicketAlreadyReservedException(command.ticketId());
        }

        Booking booking = Booking.create(
                command.userId(),
                command.ticketId(),
                ticket.eventId(),
                ticket.price(),
                command.reservationTtl()
        );

        bookingRepository.save(booking);
        booking.pullDomainEvents().forEach(publisher::publish);
        return booking.getId();
    }
}
