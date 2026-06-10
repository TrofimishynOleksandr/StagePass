package com.stagepass.shared.event.booking;

import com.stagepass.shared.domain.DomainEvent;
import com.stagepass.shared.identity.BookingId;
import com.stagepass.shared.identity.TicketId;

import java.time.LocalDateTime;

public record BookingExpiredEvent(
        BookingId bookingId,
        TicketId ticketId,
        LocalDateTime occurredAt)
        implements DomainEvent {}
