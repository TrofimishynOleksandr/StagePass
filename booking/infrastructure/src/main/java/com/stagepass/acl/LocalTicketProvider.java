package com.stagepass.acl;

import com.stagepass.exception.TicketNotFoundException;
import com.stagepass.port.TicketDetails;
import com.stagepass.port.TicketProvider;
import com.stagepass.port.TicketRepository;
import com.stagepass.shared.identity.TicketId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalTicketProvider implements TicketProvider {
    private final TicketRepository ticketRepository;

    @Override
    public TicketDetails getTicketDetails(TicketId ticketId) {
        return ticketRepository.findById(ticketId)
                .map(ticket -> new TicketDetails(
                        ticket.getId(),
                        ticket.getEventId(),
                        ticket.getVenueSeatId(),
                        ticket.getPrice(),
                        ticket.getStatus()
                ))
                .orElseThrow(() -> new TicketNotFoundException(ticketId));
    }
}
