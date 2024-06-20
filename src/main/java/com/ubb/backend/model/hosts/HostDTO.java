package com.ubb.backend.model.hosts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ubb.backend.model.Role;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;

@Getter
@Setter
public class HostDTO {

    private String name;
    private String email;
    private String password;
    private String bio;
    private String organisation;
    private String socialMediaLink;
    public Role role;

    //constructors
    public HostDTO() {
    }
    public HostDTO(String name, String email, String password, String bio, String organisation, String socialMediaLink) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.organisation = organisation;
        this.socialMediaLink = socialMediaLink;
    }
    //getters and setters
    public Host getHost() {
        //return new Host(0L, name, password, email, bio, organisation, socialMediaLink, role,  new ArrayList<>());
        return new Host(0L, name, password, email, bio, organisation, socialMediaLink, role);
    }

    public String getPassword() {
        return password;
    }


    //toString
    @Override
    public String toString() {
        return "HostDTO{" +
                "id=" + '0' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", organisation='" + organisation + '\'' +
                ", socialMediaLink='" + socialMediaLink + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
