package com.stagepass.acl;

import com.stagepass.port.VenueSeatRepository;
import com.stagepass.shared.identity.VenueId;
import com.stagepass.shared.port.VenueSeatInfo;
import com.stagepass.shared.port.VenueSeatsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalVenueSeatsProvider implements VenueSeatsProvider {
    private final VenueSeatRepository venueSeatRepository;

    public List<VenueSeatInfo> getSeatsByVenue(VenueId venueId) {
        return venueSeatRepository.findAllByVenueId(venueId)
                .stream()
                .map(venueSeat ->  new VenueSeatInfo(
                        venueSeat.getId(),
                        venueSeat.getRow(),
                        venueSeat.getSeatNumber(),
                        venueSeat.getSection()
                ))
                .toList();
    }
}
