package com.stagepass.shared.event.event;

import com.stagepass.shared.domain.DomainEvent;
import com.stagepass.shared.identity.EventId;

import java.time.LocalDateTime;

public record EventCancelledEvent(EventId eventId, LocalDateTime occurredAt)
        implements DomainEvent {}
