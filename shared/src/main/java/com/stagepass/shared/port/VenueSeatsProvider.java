package com.stagepass.shared.port;

import com.stagepass.shared.identity.VenueId;

import java.util.List;

public interface VenueSeatsProvider {
    List<VenueSeatInfo> getSeatsByVenue(VenueId venueId);
}
