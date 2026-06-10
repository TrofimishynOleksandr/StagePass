package com.stagepass.usecase;

import com.stagepass.port.BookingRepository;
import com.stagepass.shared.domain.DomainEventPublisher;
import com.stagepass.shared.identity.TicketId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExpireBookingUseCase {
    private final BookingRepository bookingRepository;
    private final DomainEventPublisher publisher;

    public void execute(TicketId ticketId) {
        bookingRepository.findPendingByTicketId(ticketId)
                .ifPresent(booking -> {
                    booking.expire();
                    bookingRepository.save(booking);
                    booking.pullDomainEvents().forEach(publisher::publish);
                });
    }
}
