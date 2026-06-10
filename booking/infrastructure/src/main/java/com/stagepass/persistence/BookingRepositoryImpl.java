package com.stagepass.persistence;

import com.stagepass.model.Booking;
import com.stagepass.model.BookingStatus;
import com.stagepass.persistence.mapper.BookingMapper;
import com.stagepass.port.BookingRepository;
import com.stagepass.shared.identity.BookingId;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {
    private final BookingJpaRepository jpa;
    private final BookingMapper mapper;

    @Override
    public void save(Booking booking) {
        jpa.save(mapper.toEntity(booking));
    }

    @Override
    public Optional<Booking> findById(BookingId id) {
        return jpa.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<Booking> findExpired() {
        return jpa.findByStatusAndBookingEndBefore(
                        BookingStatus.PENDING,
                        LocalDateTime.now()
                )
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Booking> findPendingByTicketId(TicketId ticketId) {
        return jpa.findByStatusAndTicketId(BookingStatus.PENDING, ticketId.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<Booking> findActiveByEventId(EventId eventId) {
        return jpa.findByEventIdAndStatusIn(
                        eventId.value(),
                        List.of(
                            BookingStatus.PENDING,
                            BookingStatus.CONFIRMED
                        )
                )
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
