package com.stagepass.port;

import com.stagepass.shared.model.TicketDisplayStatus;

import java.math.BigDecimal;

public record TicketInfo(
        String ticketId,
        String venueSeatId,
        BigDecimal price,
        String currency,
        TicketDisplayStatus status
) {}
