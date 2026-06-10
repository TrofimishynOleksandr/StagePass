package com.stagepass.rest.dto;

import com.stagepass.shared.model.Money;

import java.time.LocalDateTime;
import java.util.Map;

public record CreateEventRequest(
        String venueId,
        String name,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Map<String, Money> priceBySection
) {}
