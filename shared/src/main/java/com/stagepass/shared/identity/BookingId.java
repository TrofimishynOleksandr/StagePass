package com.stagepass.shared.identity;

import java.util.UUID;

public record BookingId(String value) {
    public BookingId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("BookingId cannot be blank");
        }
    }

    public static BookingId generate() {
        return new BookingId(UUID.randomUUID().toString());
    }
}
