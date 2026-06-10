package com.stagepass.usecase;

import com.stagepass.command.CreateEventCommand;
import com.stagepass.model.Event;
import com.stagepass.port.EventRepository;
import com.stagepass.shared.domain.DomainEventPublisher;
import com.stagepass.shared.identity.EventId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateEventUseCase {
    private final EventRepository eventRepository;
    private final DomainEventPublisher publisher;

    public EventId execute(CreateEventCommand command) {
        Event event = Event.create(
                command.venueId(),
                command.name(),
                command.startTime(),
                command.endTime(),
                command.priceBySection()
        );

        eventRepository.save(event);
        event.pullDomainEvents().forEach(publisher::publish);

        return event.getId();
    }
}
