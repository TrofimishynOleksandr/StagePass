package com.stagepass.persistence;

import com.stagepass.model.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "booking_bookings")
public class BookingEntity {
    @Id
    private String id;
    private String userId;
    private String ticketId;
    private String eventId;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingEnd;
    private BigDecimal amount;
    private String currency;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
