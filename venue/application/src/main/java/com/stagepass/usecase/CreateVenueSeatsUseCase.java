package com.stagepass.usecase;

import com.stagepass.command.CreateVenueSeatsCommand;
import com.stagepass.model.VenueSeat;
import com.stagepass.port.VenueSeatRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateVenueSeatsUseCase {
    private final VenueSeatRepository venueSeatRepository;

    public void execute(CreateVenueSeatsCommand command) {
        List<VenueSeat> seats = command.seats()
                .stream()
                .map(venueSeatDto -> VenueSeat.create(
                        command.venueId(),
                        venueSeatDto.row(),
                        venueSeatDto.seatNumber(),
                        venueSeatDto.section()
                ))
                .toList();

        venueSeatRepository.saveAll(seats);
    }
}

