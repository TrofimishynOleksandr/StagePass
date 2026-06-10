package com.stagepass.port;

import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;
import com.stagepass.shared.identity.UserId;

import java.time.Duration;

public interface TicketReservation {
    boolean reserve(EventId eventId, TicketId ticketId, UserId userId, Duration ttl);
    void release(TicketId ticketId);
    boolean isReserved(TicketId ticketId);
}
