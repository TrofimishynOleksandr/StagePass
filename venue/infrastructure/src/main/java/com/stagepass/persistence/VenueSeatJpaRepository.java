package com.stagepass.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueSeatJpaRepository extends JpaRepository<VenueSeatEntity, String> {
    List<VenueSeatEntity> findAllByVenueId(String venueId);
}
