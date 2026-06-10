package com.stagepass.persistence;

import com.stagepass.model.VenueSeat;
import com.stagepass.persistence.mapper.VenueSeatMapper;
import com.stagepass.port.VenueSeatRepository;
import com.stagepass.shared.identity.VenueId;
import com.stagepass.shared.identity.VenueSeatId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VenueSeatRepositoryImpl implements VenueSeatRepository {
    private final VenueSeatJpaRepository jpa;
    private final VenueSeatMapper mapper;

    @Override
    public void save(VenueSeat seat) {
        jpa.save(mapper.toEntity(seat));
    }

    @Override
    public void saveAll(List<VenueSeat> seats) {
        jpa.saveAll(mapper.toEntity(seats));
    }

    @Override
    public Optional<VenueSeat> findById(VenueSeatId id) {
        return jpa.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<VenueSeat> findAllByVenueId(VenueId venueId) {
        return mapper.toDomain(jpa.findAllByVenueId(venueId.value()));
    }
}
