package com.stagepass.model;

import com.stagepass.shared.identity.VenueId;
import com.stagepass.shared.identity.VenueSeatId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VenueSeat {
    private final VenueSeatId id;
    private final VenueId venueId;
    private final Integer row;
    private final Integer seatNumber;
    private final String section;

    public static VenueSeat create(VenueId venueId, Integer row,
                                   Integer seatNumber, String section) {
        return new VenueSeat(VenueSeatId.generate(), venueId, row, seatNumber, section);
    }
}
