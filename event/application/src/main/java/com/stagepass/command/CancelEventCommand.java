package com.stagepass.command;

import com.stagepass.shared.identity.EventId;

public record CancelEventCommand(EventId eventId) {
}
