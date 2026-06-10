package com.stagepass.usecase;

import com.stagepass.command.CancelTicketsCommand;
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
public class CancelTicketsUseCase {
    private final TicketRepository ticketRepository;
    private final DomainEventPublisher domainEventPublisher;

    public void execute(CancelTicketsCommand command) {
        List<Ticket> tickets = ticketRepository.findAllByEventId(command.eventId());
        ticketRepository.saveAll(tickets);
        tickets.forEach(ticket ->
                ticket.pullDomainEvents()
                        .forEach(domainEventPublisher::publish));
    }
}
