package com.stagepass.exception;

import com.stagepass.shared.identity.TicketId;

public class TicketAlreadySoldException extends RuntimeException {
    public TicketAlreadySoldException(TicketId ticketId) {
        super("Ticket " + ticketId.value() + " is already sold.");
    }
}
