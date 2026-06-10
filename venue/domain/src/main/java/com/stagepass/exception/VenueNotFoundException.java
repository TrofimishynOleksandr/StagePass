package com.stagepass.exception;

import com.stagepass.shared.identity.VenueId;

public class VenueNotFoundException extends RuntimeException {
    public VenueNotFoundException(VenueId venueId) {
        super("Venue " + venueId.value() + " is not found");
    }
}
