package com.stagepass.shared.domain;

import java.time.LocalDateTime;

public interface DomainEvent {
    LocalDateTime occurredAt();
}
