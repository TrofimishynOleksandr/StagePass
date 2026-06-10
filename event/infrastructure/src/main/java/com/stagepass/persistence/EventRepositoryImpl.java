package com.stagepass.persistence;

import com.stagepass.model.Event;
import com.stagepass.persistence.mapper.EventMapper;
import com.stagepass.port.EventRepository;
import com.stagepass.shared.identity.EventId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {
    private final EventJpaRepository jpa;
    private final EventMapper mapper;

    @Override
    public void save(Event event) {
        jpa.save(mapper.toEntity(event));
    }

    @Override
    public Optional<Event> findById(EventId id) {
        return jpa.findById(id.value())
                .map(mapper::toDomain);
    }
}
