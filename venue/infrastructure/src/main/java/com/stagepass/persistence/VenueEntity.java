package com.stagepass.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "venue_venues")
public class VenueEntity {
    @Id
    private String id;
    private String name;
    private String city;
    private String street;
}
