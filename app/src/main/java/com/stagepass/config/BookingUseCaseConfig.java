package com.stagepass.config;

import com.stagepass.port.BookingRepository;
import com.stagepass.port.TicketProvider;
import com.stagepass.port.TicketReservation;
import com.stagepass.shared.domain.DomainEventPublisher;
import com.stagepass.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingUseCaseConfig {
    @Bean
    public ExpireBookingUseCase expireBookingUseCase(
            BookingRepository repo,
            DomainEventPublisher publisher
    ) {
        return new ExpireBookingUseCase(repo, publisher);
    }

    @Bean
    public CreateBookingUseCase createBookingUseCase(
            BookingRepository bookingRepository,
            DomainEventPublisher publisher,
            TicketProvider ticketProvider,
            TicketReservation ticketReservation
    ) {
        return new CreateBookingUseCase(bookingRepository, publisher, ticketProvider, ticketReservation);
    }

    @Bean
    public CancelBookingsForEventUseCase cancelBookingsForEventUseCase(
            BookingRepository repo,
            DomainEventPublisher publisher
    ) {
        return new CancelBookingsForEventUseCase(repo, publisher);
    }

    @Bean
    public CancelBookingUseCase cancelBookingUseCase(
            BookingRepository repo,
            DomainEventPublisher publisher
    ) {
        return new CancelBookingUseCase(repo, publisher);
    }

    @Bean
    public GetBookingUseCase getBookingUseCase(
            BookingRepository repo
    ) {
        return new GetBookingUseCase(repo);
    }
}