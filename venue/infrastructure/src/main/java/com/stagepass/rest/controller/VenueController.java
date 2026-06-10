package com.stagepass.rest.controller;


import com.stagepass.command.CreateVenueCommand;
import com.stagepass.command.CreateVenueSeatsCommand;
import com.stagepass.command.VenueSeatDto;
import com.stagepass.model.Venue;
import com.stagepass.model.VenueSeat;
import com.stagepass.query.GetVenueQuery;
import com.stagepass.query.GetVenueSeatsQuery;
import com.stagepass.rest.dto.*;
import com.stagepass.shared.identity.VenueId;
import com.stagepass.usecase.CreateVenueSeatsUseCase;
import com.stagepass.usecase.CreateVenueUseCase;
import com.stagepass.usecase.GetVenueSeatsUseCase;
import com.stagepass.usecase.GetVenueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {
    private final CreateVenueUseCase createVenueUseCase;
    private final CreateVenueSeatsUseCase createVenueSeatsUseCase;
    private final GetVenueUseCase getVenueUseCase;
    private final GetVenueSeatsUseCase getVenueSeatsUseCase;

    @PostMapping
    public ResponseEntity<String> createVenue(@RequestBody CreateVenueRequest request) {
        CreateVenueCommand command = new CreateVenueCommand(
                request.name(),
                request.address()
        );

        VenueId venueId = createVenueUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(venueId.value());
    }

    @PostMapping("/{id}/seats")
    public ResponseEntity<Void> createVenueSeats(@PathVariable String id, @RequestBody CreateVenueSeatsRequest request) {
        CreateVenueSeatsCommand command = new CreateVenueSeatsCommand(
                new VenueId(id),
                request.seats()
                        .stream()
                        .map(s -> new VenueSeatDto(
                                s.row(),
                                s.seatNumber(),
                                s.section())
                        )
                        .toList()
        );

        createVenueSeatsUseCase.execute(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> getVenue(@PathVariable String id) {
        Venue venue = getVenueUseCase.execute(new GetVenueQuery(new VenueId(id)));
        return ResponseEntity.ok(new VenueResponse(
                venue.getId().value(),
                venue.getName(),
                venue.getAddress()
        ));
    }

    @GetMapping("/{id}/seats")
    public ResponseEntity<VenueSeatsResponse> getVenueSeats(@PathVariable String id) {
        List<VenueSeat> venueSeats = getVenueSeatsUseCase.execute(new GetVenueSeatsQuery(new VenueId(id)));
        return ResponseEntity.ok(new VenueSeatsResponse(
                venueSeats
                        .stream()
                        .map(
                        venueSeat -> new VenueSeatResponse(
                                venueSeat.getId().value(),
                                venueSeat.getVenueId().value(),
                                venueSeat.getRow(),
                                venueSeat.getSeatNumber(),
                                venueSeat.getSection()
                        ))
                        .toList()
        ));
    }
}
