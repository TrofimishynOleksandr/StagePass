package com.stagepass.acl;

import com.stagepass.port.TicketInfo;
import com.stagepass.port.TicketRepository;
import com.stagepass.port.TicketsProvider;
import com.stagepass.redis.RedisTicketReservationChecker;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.model.TicketDisplayStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalTicketsProvider implements TicketsProvider {
    private final TicketRepository ticketRepository;
    private final RedisTicketReservationChecker ticketReservationChecker;

    @Override
    public List<TicketInfo> getTicketsByEvent(EventId eventId) {
        return ticketRepository.findAllByEventId(eventId)
                .stream()
                .map(ticket -> new TicketInfo(
                        ticket.getId().value(),
                        ticket.getVenueSeatId().value(),
                        ticket.getPrice().amount(),
                        ticket.getPrice().currency(),
                        ticketReservationChecker.isReserved(ticket.getId())
                                ? TicketDisplayStatus.RESERVED
                                : TicketDisplayStatus.valueOf(ticket.getStatus().name())
                ))
                .toList();
    }
}
