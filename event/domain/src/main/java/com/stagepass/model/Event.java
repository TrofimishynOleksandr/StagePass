package com.stagepass.model;


import com.stagepass.shared.domain.AggregateRoot;
import com.stagepass.shared.event.event.EventCancelledEvent;
import com.stagepass.shared.event.event.EventCreatedEvent;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.VenueId;
import com.stagepass.shared.model.Money;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class Event extends AggregateRoot {
    private final EventId id;
    private final VenueId venueId;
    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    @NonNull
    private EventStatus status;

    public static Event create(VenueId venueId, String name,
                                 LocalDateTime startTime, LocalDateTime endTime,
                                 Map<String, Money> priceBySection) {
        Event event = new Event(
                EventId.generate(),
                venueId,
                name,
                startTime,
                endTime,
                EventStatus.CREATED
        );

        event.registerEvent(new EventCreatedEvent(event.id, venueId, priceBySection, LocalDateTime.now()));

        return event;
    }

    public void cancel() {
        if (status == EventStatus.CANCELLED) {
            throw new IllegalStateException("Already cancelled");
        }
        this.status = EventStatus.CANCELLED;
        registerEvent(new EventCancelledEvent(this.id, LocalDateTime.now()));
    }
}
