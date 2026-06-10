package com.stagepass.usecase;

import com.stagepass.exception.BookingNotFoundException;
import com.stagepass.model.Booking;
import com.stagepass.port.BookingRepository;
import com.stagepass.shared.identity.BookingId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetBookingUseCase {
    private final BookingRepository bookingRepository;

    public Booking execute(BookingId id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }
}
