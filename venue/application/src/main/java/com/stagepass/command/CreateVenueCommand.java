package com.stagepass.command;

import com.stagepass.shared.model.Address;

public record CreateVenueCommand(
        String name,
        Address address
) {}
