package com.stagepass.usecase;

import com.stagepass.exception.BookingNotFoundException;
import com.stagepass.model.Booking;
import com.stagepass.port.BookingRepository;
import com.stagepass.shared.domain.DomainEventPublisher;
import com.stagepass.shared.identity.BookingId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CancelBookingUseCase {
    private final BookingRepository bookingRepository;
    private final DomainEventPublisher publisher;

    public void execute(BookingId bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));

        booking.cancel();
        bookingRepository.save(booking);
        booking.pullDomainEvents().forEach(publisher::publish);
    }
}
