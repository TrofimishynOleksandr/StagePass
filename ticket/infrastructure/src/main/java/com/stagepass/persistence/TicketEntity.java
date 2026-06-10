package com.stagepass.persistence;

import com.stagepass.shared.model.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "ticket_tickets")
public class TicketEntity {
    @Id
    private String id;
    private String eventId;
    private String venueSeatId;
    private BigDecimal price;
    private String currency;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
