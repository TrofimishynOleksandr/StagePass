package com.stagepass.port;

import com.stagepass.model.Ticket;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    void save(Ticket ticket);
    void saveAll(List<Ticket> tickets);
    List<Ticket> findAllByEventId(EventId eventId);
    Optional<Ticket> findById(TicketId id);
}
