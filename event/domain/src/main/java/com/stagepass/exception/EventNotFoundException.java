package com.stagepass.exception;

import com.stagepass.shared.identity.EventId;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(EventId eventId) {
        super("Event " + eventId.value() + " is not found");
    }
}
