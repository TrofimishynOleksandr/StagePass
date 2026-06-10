package com.stagepass.port;

import com.stagepass.model.Event;
import com.stagepass.shared.identity.EventId;

import java.util.Optional;

public interface EventRepository {
    void save(Event event);
    Optional<Event> findById(EventId id);
}
