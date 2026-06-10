package com.stagepass.config;

import com.stagepass.port.EventRepository;
import com.stagepass.shared.domain.DomainEventPublisher;
import com.stagepass.usecase.CancelEventUseCase;
import com.stagepass.usecase.CreateEventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventUseCaseConfig {

    @Bean
    public CreateEventUseCase createEventUseCase(
            EventRepository eventRepository,
            DomainEventPublisher publisher
    ) {
        return new CreateEventUseCase(eventRepository, publisher);
    }

    @Bean
    public CancelEventUseCase cancelEventUseCase(
            EventRepository eventRepository,
            DomainEventPublisher publisher
    ) {
        return new CancelEventUseCase(eventRepository, publisher);
    }
}