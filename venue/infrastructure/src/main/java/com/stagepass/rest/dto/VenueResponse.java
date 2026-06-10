package com.stagepass.rest.dto;

import com.stagepass.shared.model.Address;

public record VenueResponse(String id, String name, Address address) {
}
