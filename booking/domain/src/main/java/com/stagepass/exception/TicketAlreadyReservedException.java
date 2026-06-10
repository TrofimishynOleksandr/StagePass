package com.stagepass.exception;

import com.stagepass.shared.identity.TicketId;

public class TicketAlreadyReservedException extends RuntimeException {
    public TicketAlreadyReservedException(TicketId ticketId) {
        super("Ticket " + ticketId.value() + " is already reserved.");
    }
}
