package com.stagepass.model;


import com.stagepass.shared.domain.AggregateRoot;
import com.stagepass.shared.identity.VenueId;
import com.stagepass.shared.model.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Venue extends AggregateRoot {
    private final VenueId id;
    private final String name;
    private final Address address;

    public static Venue create(String name, Address address) {
        return new Venue(VenueId.generate(), name, address);
    }
}
