package com.stagepass.port;

import com.stagepass.shared.identity.TicketId;

public interface TicketReservationChecker {
    boolean isReserved(TicketId ticketId);
}
