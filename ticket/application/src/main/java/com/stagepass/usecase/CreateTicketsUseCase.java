package com.stagepass.usecase;

import com.stagepass.command.CreateTicketsCommand;
import com.stagepass.exception.SectionPriceNotFoundException;
import com.stagepass.model.Ticket;
import com.stagepass.port.TicketRepository;
import com.stagepass.shared.domain.DomainEventPublisher;
import com.stagepass.shared.model.Money;
import com.stagepass.shared.port.VenueSeatInfo;
import com.stagepass.shared.port.VenueSeatsProvider;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateTicketsUseCase {
    private final TicketRepository ticketRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final VenueSeatsProvider venueSeatsProvider;

    public void execute(CreateTicketsCommand command) {
        List<VenueSeatInfo> seats = venueSeatsProvider.getSeatsByVenue(command.venueId());

        List<Ticket> tickets = seats
                .stream()
                .map(venueSeatInfo -> {
                    Money price = command.priceBySection().get(venueSeatInfo.section());
                    if (price == null) {
                        throw new SectionPriceNotFoundException(venueSeatInfo.section());
                    }
                    return Ticket.create(command.eventId(), venueSeatInfo.id(), price);
                })
                .toList();

        ticketRepository.saveAll(tickets);

        tickets.forEach(ticket -> ticket.pullDomainEvents().forEach(domainEventPublisher::publish));
    }
}
