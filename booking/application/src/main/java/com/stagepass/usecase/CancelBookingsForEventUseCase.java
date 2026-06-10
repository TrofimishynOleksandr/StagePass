package com.stagepass.usecase;

import com.stagepass.model.Booking;
import com.stagepass.port.BookingRepository;
import com.stagepass.shared.domain.DomainEventPublisher;
import com.stagepass.shared.identity.EventId;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CancelBookingsForEventUseCase {
    private final BookingRepository bookingRepository;
    private final DomainEventPublisher publisher;

    public void execute(EventId eventId) {
        List<Booking> bookings = bookingRepository.findActiveByEventId(eventId);
        bookings.forEach(booking -> {
            booking.cancel();
            bookingRepository.save(booking);
            booking.pullDomainEvents().forEach(publisher::publish);
        });
    }
}
