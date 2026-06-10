package com.stagepass.rest.dto;

public record VenueSeatResponse(
        String id,
        String venueId,
        Integer row,
        Integer seatNumber,
        String section
) {}
