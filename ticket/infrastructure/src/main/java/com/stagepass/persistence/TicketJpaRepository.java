package com.stagepass.persistence;

import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.VenueSeatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketJpaRepository extends JpaRepository<TicketEntity, String> {
    List<TicketEntity> findAllByEventId(String eventId);
    Optional<TicketEntity> findByEventIdAndVenueSeatId(String eventId, String venueSeatId);
}
