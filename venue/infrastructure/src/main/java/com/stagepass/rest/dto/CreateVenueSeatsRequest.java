package com.stagepass.rest.dto;

import java.util.List;

public record CreateVenueSeatsRequest(
        List<VenueSeatRequest> seats
) {}
