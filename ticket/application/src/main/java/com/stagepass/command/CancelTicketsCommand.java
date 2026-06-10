package com.stagepass.command;

import com.stagepass.shared.identity.EventId;

public record CancelTicketsCommand(EventId eventId) {
}
