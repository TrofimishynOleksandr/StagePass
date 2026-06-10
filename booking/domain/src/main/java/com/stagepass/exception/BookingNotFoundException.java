package com.stagepass.exception;

import com.stagepass.shared.identity.BookingId;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(BookingId bookingId) {
        super("Booking " + bookingId.value() + " is not found");
    }
}
