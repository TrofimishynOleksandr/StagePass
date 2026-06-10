package com.stagepass.command;

import com.stagepass.shared.identity.VenueId;

import java.util.List;

public record CreateVenueSeatsCommand(
        VenueId venueId,
        List<VenueSeatDto> seats
) {}
