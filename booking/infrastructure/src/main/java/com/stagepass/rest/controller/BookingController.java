package com.stagepass.rest.controller;

import com.stagepass.command.CreateBookingCommand;
import com.stagepass.model.Booking;
import com.stagepass.properties.BookingProperties;
import com.stagepass.rest.dto.CreateBookingRequest;
import com.stagepass.shared.identity.BookingId;
import com.stagepass.shared.identity.TicketId;
import com.stagepass.shared.identity.UserId;
import com.stagepass.usecase.CancelBookingUseCase;
import com.stagepass.usecase.CreateBookingUseCase;
import com.stagepass.usecase.GetBookingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final CreateBookingUseCase createBookingUseCase;
    private final GetBookingUseCase getBookingUseCase;
    private final CancelBookingUseCase cancelBookingUseCase;
    private final BookingProperties bookingProperties;

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody CreateBookingRequest request) {
        CreateBookingCommand command = new CreateBookingCommand(
                new UserId(request.userId()),
                new TicketId(request.ticketId()),
                Duration.ofMinutes(bookingProperties.getReservationTtlMinutes())
        );
        BookingId bookingId = createBookingUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingId.value());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable("id") String id) {
        return ResponseEntity.ok(getBookingUseCase.execute(new BookingId(id)));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable("id") String id) {
        cancelBookingUseCase.execute(new BookingId(id));
        return ResponseEntity.ok().build();
    }
}
