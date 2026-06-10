package com.stagepass.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "venue_seats")
public class VenueSeatEntity {
    @Id
    private String id;
    private String venueId;
    private Integer row;
    private Integer seatNumber;
    private String section;
}
