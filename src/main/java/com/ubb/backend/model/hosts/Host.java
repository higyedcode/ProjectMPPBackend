package com.ubb.backend.model.hosts;

import java.util.List;

import com.ubb.backend.model.events.Event;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;



@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "hosts")
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "bio")
    private String bio;

    @Column(name = "org")
    private String organisation;

    @Column(name="socialMediaLink")
    private String socialMediaLink;
    
    @OneToMany(mappedBy = "host", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Event> events;

}
