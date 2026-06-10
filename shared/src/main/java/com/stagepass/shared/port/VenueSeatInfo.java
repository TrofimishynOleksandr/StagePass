package com.stagepass.shared.port;

import com.stagepass.shared.identity.VenueSeatId;

public record VenueSeatInfo(
        VenueSeatId id,
        Integer row,
        Integer seatNumber,
        String section
) {}
