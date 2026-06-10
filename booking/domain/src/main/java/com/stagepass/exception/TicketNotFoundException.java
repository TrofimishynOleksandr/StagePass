package com.stagepass.exception;

import com.stagepass.shared.identity.TicketId;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(TicketId ticketId) {
        super("Ticket " + ticketId.value() + " not found.");
    }
}
