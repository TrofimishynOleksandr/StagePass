package com.stagepass.command;

import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.VenueId;
import com.stagepass.shared.model.Money;

import java.util.Map;

public record CreateTicketsCommand(
        EventId eventId,
        VenueId venueId,
        Map<String, Money> priceBySection
) {}
