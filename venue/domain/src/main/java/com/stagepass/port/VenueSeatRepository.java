package com.stagepass.port;

import com.stagepass.model.VenueSeat;
import com.stagepass.shared.identity.VenueId;
import com.stagepass.shared.identity.VenueSeatId;

import java.util.List;
import java.util.Optional;

public interface VenueSeatRepository {
    void save(VenueSeat seat);
    void saveAll(List<VenueSeat> seats);
    Optional<VenueSeat> findById(VenueSeatId id);
    List<VenueSeat> findAllByVenueId(VenueId venueId);
}
