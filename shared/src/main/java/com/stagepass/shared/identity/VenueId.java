package com.stagepass.shared.identity;

import java.util.UUID;

public record VenueId(String value) {
    public VenueId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("VenueId cannot be blank");
        }
    }

    public static VenueId generate() {
        return new VenueId(UUID.randomUUID().toString());
    }
}
