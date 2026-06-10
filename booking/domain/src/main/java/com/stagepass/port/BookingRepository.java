package com.stagepass.port;

import com.stagepass.model.Booking;
import com.stagepass.shared.identity.BookingId;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    void save(Booking booking);
    Optional<Booking> findById(BookingId id);
    List<Booking> findExpired();
    Optional<Booking> findPendingByTicketId(TicketId ticketId);
    List<Booking> findActiveByEventId(EventId eventId);
}
