package com.stagepass.rest.dto;

public record VenueSeatRequest(
        Integer row,
        Integer seatNumber,
        String section
) {}
