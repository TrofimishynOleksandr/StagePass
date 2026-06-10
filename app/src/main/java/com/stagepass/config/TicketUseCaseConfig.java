package com.stagepass.config;

import com.stagepass.port.TicketRepository;
import com.stagepass.shared.domain.DomainEventPublisher;
import com.stagepass.shared.port.VenueSeatsProvider;
import com.stagepass.usecase.CancelTicketsUseCase;
import com.stagepass.usecase.CreateTicketsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketUseCaseConfig {

    @Bean
    public CreateTicketsUseCase createTicketsUseCase(
            TicketRepository ticketRepository,
            DomainEventPublisher publisher,
            VenueSeatsProvider venueSeatsProvider
    ) {
        return new CreateTicketsUseCase(ticketRepository, publisher, venueSeatsProvider);
    }

    @Bean
    public CancelTicketsUseCase cancelTicketsUseCase(
            TicketRepository ticketRepository,
            DomainEventPublisher publisher
    ) {
        return new CancelTicketsUseCase(ticketRepository, publisher);
    }
}
