package com.ubb.backend.model.hosts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ubb.backend.model.Role;
import com.ubb.backend.model.events.Event;

import jakarta.persistence.*;
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

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "bio")
    private String bio;

    @Column(name = "org")
    private String organisation;

    @Column(name="socialMediaLink")
    private String socialMediaLink;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;
    
//    @OneToMany(mappedBy = "host", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
//    private List<Event> events;

//    public int countEvents()
//    {
//        return events != null ? events.size() : 0;
//    }

}
