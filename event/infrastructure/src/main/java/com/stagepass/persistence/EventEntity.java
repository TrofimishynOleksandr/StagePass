package com.stagepass.persistence;

import com.stagepass.model.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "event_events")
public class EventEntity {
    @Id
    private String id;
    private String venueId;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
}
