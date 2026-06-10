package com.stagepass.rest.dto;

import com.stagepass.shared.model.Address;

public record CreateVenueRequest(
        String name,
        Address address
) {}
