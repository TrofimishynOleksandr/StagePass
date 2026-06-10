package com.stagepass.shared.event.event;

import com.stagepass.shared.domain.DomainEvent;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.VenueId;
import com.stagepass.shared.model.Money;

import java.time.LocalDateTime;
import java.util.Map;

public record EventCreatedEvent(EventId eventId, VenueId venueId,
                                Map<String, Money> priceBySection, LocalDateTime occurredAt)
        implements DomainEvent {}
