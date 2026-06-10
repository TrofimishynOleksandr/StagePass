package com.stagepass.config;

import com.stagepass.port.VenueRepository;
import com.stagepass.port.VenueSeatRepository;
import com.stagepass.usecase.CreateVenueSeatsUseCase;
import com.stagepass.usecase.CreateVenueUseCase;
import com.stagepass.usecase.GetVenueSeatsUseCase;
import com.stagepass.usecase.GetVenueUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VenueUseCaseConfig {

    @Bean
    public CreateVenueUseCase createVenueUseCase(VenueRepository venueRepository) {
        return new CreateVenueUseCase(venueRepository);
    }

    @Bean
    public CreateVenueSeatsUseCase createVenueSeatsUseCase(
            VenueSeatRepository venueSeatRepository
    ) {
        return new CreateVenueSeatsUseCase(venueSeatRepository);
    }

    @Bean
    public GetVenueUseCase getVenueUseCase(VenueRepository venueRepository) {
        return new GetVenueUseCase(venueRepository);
    }

    @Bean
    public GetVenueSeatsUseCase getVenueSeatsUseCase(
            VenueSeatRepository venueSeatRepository
    ) {
        return new GetVenueSeatsUseCase(venueSeatRepository);
    }
}
