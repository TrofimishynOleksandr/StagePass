package com.stagepass.shared.identity;

import java.util.UUID;

public record VenueSeatId(String value) {
    public VenueSeatId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("VenueSeatId cannot be blank");
        }
    }

    public static VenueSeatId generate() {
        return new VenueSeatId(UUID.randomUUID().toString());
    }
}
