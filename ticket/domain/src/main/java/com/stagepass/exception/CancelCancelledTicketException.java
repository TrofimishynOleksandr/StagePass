package com.stagepass.exception;

import com.stagepass.shared.identity.TicketId;

public class CancelCancelledTicketException extends RuntimeException {
    public CancelCancelledTicketException(TicketId ticketId) {
        super("Ticket " + ticketId.value() + " is already cancelled.");
    }
}
