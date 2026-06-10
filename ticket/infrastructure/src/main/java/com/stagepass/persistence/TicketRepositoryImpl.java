package com.stagepass.persistence;

import com.stagepass.model.Ticket;
import com.stagepass.persistence.mapper.TicketMapper;
import com.stagepass.port.TicketRepository;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
    private final TicketJpaRepository jpa;
    private final TicketMapper mapper;

    @Override
    public void save(Ticket ticket) {
        jpa.save(mapper.toEntity(ticket));
    }

    @Override
    public void saveAll(List<Ticket> tickets) {
        jpa.saveAll(mapper.toEntity(tickets));
    }

    @Override
    public List<Ticket> findAllByEventId(EventId eventId) {
        return mapper.toDomain(jpa.findAllByEventId(eventId.value()));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Ticket> findById(TicketId id) {
        return jpa.findById(id.value())
                .map(mapper::toDomain);
    }
}
