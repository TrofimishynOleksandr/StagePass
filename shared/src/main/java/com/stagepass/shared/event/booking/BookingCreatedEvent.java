package com.stagepass.shared.event.booking;

import com.stagepass.shared.domain.DomainEvent;
import com.stagepass.shared.identity.BookingId;

import java.time.LocalDateTime;

public record BookingCreatedEvent(BookingId eventId, LocalDateTime occurredAt)
        implements DomainEvent {}
