package com.stagepass.command;

import com.stagepass.shared.identity.TicketId;
import com.stagepass.shared.identity.UserId;

import java.time.Duration;

public record CreateBookingCommand(
        UserId userId,
        TicketId ticketId,
        Duration reservationTtl
) {}
