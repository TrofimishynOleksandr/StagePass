package com.stagepass.persistence;

import com.stagepass.model.BookingStatus;
import com.stagepass.shared.identity.EventId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, String> {
    List<BookingEntity> findByStatusAndBookingEndBefore(BookingStatus status, LocalDateTime dateTime);
    Optional<BookingEntity> findByStatusAndTicketId(BookingStatus status, String ticketId);
    List<BookingEntity> findByEventIdAndStatusIn(String eventId, List<BookingStatus> statuses);
}
