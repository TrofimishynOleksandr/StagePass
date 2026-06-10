package com.stagepass.port;

import com.stagepass.model.Venue;
import com.stagepass.shared.identity.VenueId;

import java.util.Optional;

public interface VenueRepository {
    void save(Venue venue);
    Optional<Venue> findById(VenueId id);
}
