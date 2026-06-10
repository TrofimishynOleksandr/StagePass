package com.stagepass.shared.identity;

import java.util.UUID;

public record TicketId(String value) {
    public TicketId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("TicketId cannot be blank");
        }
    }

    public static TicketId generate() {
        return new TicketId(UUID.randomUUID().toString());
    }
}
