package com.stagepass.rest.dto;

public record CreateBookingRequest(
        String userId,
        String ticketId
) {}
