package com.stagepass.port;

import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;
import com.stagepass.shared.identity.VenueSeatId;
import com.stagepass.shared.model.Money;
import com.stagepass.shared.model.TicketStatus;

public record TicketDetails(
        TicketId ticketId,
        EventId eventId,
        VenueSeatId venueSeatId,
        Money price,
        TicketStatus status
) {}
