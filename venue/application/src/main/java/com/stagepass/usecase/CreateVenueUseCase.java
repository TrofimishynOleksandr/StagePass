package com.stagepass.usecase;

import com.stagepass.command.CreateVenueCommand;
import com.stagepass.model.Venue;
import com.stagepass.port.VenueRepository;
import com.stagepass.shared.identity.VenueId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateVenueUseCase {
    private final VenueRepository venueRepository;

    public VenueId execute(CreateVenueCommand command) {
        Venue venue = Venue.create(
                command.name(),
                command.address()
        );

        venueRepository.save(venue);

        return venue.getId();
    }
}
