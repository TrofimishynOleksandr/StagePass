package com.stagepass.model;


import com.stagepass.shared.domain.AggregateRoot;
import com.stagepass.shared.event.booking.BookingCancelledEvent;
import com.stagepass.shared.event.booking.BookingCreatedEvent;
import com.stagepass.shared.event.booking.BookingExpiredEvent;
import com.stagepass.shared.identity.BookingId;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;
import com.stagepass.shared.identity.UserId;
import com.stagepass.shared.model.Money;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class Booking extends AggregateRoot {
    private final BookingId id;
    private final UserId userId;
    private final TicketId ticketId;
    private final EventId eventId;
    private final LocalDateTime bookingStart;
    private final LocalDateTime bookingEnd;
    private final Money amount;
    @NonNull
    private BookingStatus status;

    public static Booking create(UserId userId, TicketId ticketId,
                                 EventId eventId, Money amount, Duration ttl) {
        LocalDateTime now = LocalDateTime.now();
        Booking booking = new Booking(
                BookingId.generate(),
                userId, ticketId, eventId,
                now,
                now.plus(ttl),
                amount,
                BookingStatus.PENDING
        );
        booking.registerEvent(new BookingCreatedEvent(booking.id, now));
        return booking;
    }

    public void cancel() {
        if (status != BookingStatus.PENDING) {
            throw new IllegalStateException("Only pending booking can be cancelled");
        }
        this.status = BookingStatus.CANCELLED;
        registerEvent(new BookingCancelledEvent(this.id, this.ticketId, LocalDateTime.now()));
    }

    public void expire() {
        if (status != BookingStatus.PENDING) {
            throw new IllegalStateException("Only pending booking can expire");
        }
        this.status = BookingStatus.EXPIRED;
        registerEvent(new BookingExpiredEvent(this.id, this.ticketId, LocalDateTime.now()));
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(bookingEnd);
    }
}
