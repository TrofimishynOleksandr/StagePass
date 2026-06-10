package com.stagepass.port;

import com.stagepass.shared.identity.EventId;

import java.util.List;

public interface TicketsProvider {
    List<TicketInfo> getTicketsByEvent(EventId eventId);
}
