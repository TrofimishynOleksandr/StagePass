package com.stagepass.usecase;

import com.stagepass.exception.VenueNotFoundException;
import com.stagepass.model.Venue;
import com.stagepass.port.VenueRepository;
import com.stagepass.query.GetVenueQuery;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetVenueUseCase {
    private final VenueRepository venueRepository;

    public Venue execute(GetVenueQuery query) {
        return venueRepository.findById(query.id())
                .orElseThrow(() -> new VenueNotFoundException(query.id()));
    }
}
