package com.stagepass.command;

public record VenueSeatDto(
        Integer row,
        Integer seatNumber,
        String section
) {}
