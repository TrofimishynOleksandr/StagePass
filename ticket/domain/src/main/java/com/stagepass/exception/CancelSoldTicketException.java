package com.stagepass.exception;

import com.stagepass.shared.identity.TicketId;

public class CancelSoldTicketException extends RuntimeException {
    public CancelSoldTicketException(TicketId ticketId) {
        super("Ticket " + ticketId.value() + " is already sold.");
    }
}
