package com.stagepass.persistence;

import com.stagepass.model.Venue;
import com.stagepass.persistence.mapper.VenueMapper;
import com.stagepass.port.VenueRepository;
import com.stagepass.shared.identity.VenueId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VenueRepositoryImpl implements VenueRepository {
    private final VenueJpaRepository jpa;
    private final VenueMapper mapper;

    @Override
    public void save(Venue venue) {
        jpa.save(mapper.toEntity(venue));
    }

    @Override
    public Optional<Venue> findById(VenueId id) {
        return jpa.findById(id.value())
                .map(mapper::toDomain);
    }
}
