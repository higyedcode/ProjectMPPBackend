package com.ubb.backend.model.events;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ubb.backend.model.hosts.Host;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import jakarta.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String eventName;
    @Column(name = "date")
    private LocalDate eventDate;
    @Column(name = "location")
    private String eventLocation;

    @Column(name="longitude")
    private Double longitude;

    @Column(name="latitude")
    private Double latitude;

    @ManyToOne
    @JoinColumn(name="host_id", nullable = false)
    @JsonIgnore
    private Host host;

}
