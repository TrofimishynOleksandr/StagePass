package com.stagepass.usecase;

import com.stagepass.command.CancelEventCommand;
import com.stagepass.exception.EventNotFoundException;
import com.stagepass.model.Event;
import com.stagepass.port.EventRepository;
import com.stagepass.shared.domain.DomainEventPublisher;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CancelEventUseCase {
    private final EventRepository eventRepository;
    private final DomainEventPublisher publisher;

    public void execute(CancelEventCommand command) {
        Event event = eventRepository.findById(command.eventId())
                .orElseThrow(() -> new EventNotFoundException(command.eventId()));

        event.cancel();
        eventRepository.save(event);
        event.pullDomainEvents().forEach(publisher::publish);
    }
}
