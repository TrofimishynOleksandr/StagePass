package com.stagepass.usecase;

import com.stagepass.model.VenueSeat;
import com.stagepass.port.VenueSeatRepository;
import com.stagepass.query.GetVenueSeatsQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetVenueSeatsUseCase {
    private final VenueSeatRepository venueSeatRepository;

    public List<VenueSeat> execute(GetVenueSeatsQuery query) {
        return venueSeatRepository.findAllByVenueId(query.id());
    }
}
