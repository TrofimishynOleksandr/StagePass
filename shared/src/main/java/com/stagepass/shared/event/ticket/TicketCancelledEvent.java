package com.stagepass.shared.event.ticket;

import com.stagepass.shared.domain.DomainEvent;
import com.stagepass.shared.identity.TicketId;

import java.time.LocalDateTime;

public record TicketCancelledEvent(TicketId ticketId, LocalDateTime occurredAt)
        implements DomainEvent {}
