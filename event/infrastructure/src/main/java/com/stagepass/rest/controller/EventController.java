package com.stagepass.rest.controller;

import com.stagepass.acl.LocalTicketsProvider;
import com.stagepass.command.CancelEventCommand;
import com.stagepass.command.CreateEventCommand;
import com.stagepass.model.Ticket;
import com.stagepass.port.TicketInfo;
import com.stagepass.port.TicketsProvider;
import com.stagepass.rest.dto.CreateEventRequest;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.VenueId;
import com.stagepass.usecase.CancelEventUseCase;
import com.stagepass.usecase.CreateEventUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final CreateEventUseCase createEventUseCase;
    private final CancelEventUseCase cancelEventUseCase;
    private final TicketsProvider ticketsProvider;

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody CreateEventRequest request) {
        CreateEventCommand command = new CreateEventCommand(
                new VenueId(request.venueId()),
                request.name(),
                request.startTime(),
                request.endTime(),
                request.priceBySection()
        );

        EventId eventId = createEventUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventId.value());
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelEvent(@PathVariable String id) {
        cancelEventUseCase.execute(new CancelEventCommand(new EventId(id)));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<TicketInfo>> getTickets(@PathVariable("id") String id) {
        return ResponseEntity.ok(ticketsProvider.getTicketsByEvent(new EventId(id)));
    }
}
