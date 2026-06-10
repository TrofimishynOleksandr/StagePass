package com.stagepass.port;

import com.stagepass.shared.identity.TicketId;

public interface TicketProvider {
    TicketDetails getTicketDetails(TicketId ticketId);
}
