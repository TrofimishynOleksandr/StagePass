package com.stagepass.infrastructure;

import com.stagepass.shared.domain.DomainEvent;
import com.stagepass.shared.domain.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SpringDomainEventPublisher implements DomainEventPublisher {
    private final ApplicationEventPublisher springPublisher;

    @Override
    public void publish(DomainEvent event) {
        springPublisher.publishEvent(event);
    }

    @Override
    public void publishAll(@NonNull List<DomainEvent> events) {
        events.forEach(springPublisher::publishEvent);
    }
}