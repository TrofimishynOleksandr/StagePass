package com.stagepass.command;

import com.stagepass.shared.identity.VenueId;
import com.stagepass.shared.model.Money;

import java.time.LocalDateTime;
import java.util.Map;

public record CreateEventCommand(
        VenueId venueId,
        String name,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Map<String, Money> priceBySection
) {}
