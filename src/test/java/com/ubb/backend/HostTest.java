package com.ubb.backend;

import com.ubb.backend.model.events.Event;
import com.ubb.backend.model.hosts.Host;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HostTest {
    @Test
    public void testConstructorAndGetters() {
        // Create a Host object using the constructor
        Host host = new Host(1L, "John Doe", "john@example.com", "Bio", "Organization", "Social Media Link", new ArrayList<Event>());

        // Test getters
        assertEquals("1", host.getId().toString());
        assertEquals("John Doe", host.getName());
        assertEquals("john@example.com", host.getEmail());
        assertEquals("Bio", host.getBio());
        assertEquals("Organization", host.getOrganisation());
        assertEquals("Social Media Link", host.getSocialMediaLink());
    }

    @Test
    public void testToString() {
        // Create a Host object
        Host host = new Host(1L, "John Doe", "john@example.com", "Bio", "Organization", "Social Media Link", new ArrayList<Event>());

        // Test toString method
        String expectedToString = "Host(id=1, name=John Doe, email=john@example.com, bio=Bio, organisation=Organization, socialMediaLink=Social Media Link, events=[])";
        assertEquals(expectedToString, host.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two Host objects with the same attributes
        Host host1 = new Host(1L, "John Doe", "john@example.com", "Bio", "Organization", "Social Media Link", new ArrayList<Event>());
        Host host2 = new Host(1L, "John Doe", "john@example.com", "Bio", "Organization", "Social Media Link", new ArrayList<Event>());

        // Test equals method
        assertEquals(host1, host2);

        // Test hash code
        assertEquals(host1.hashCode(), host2.hashCode());
    }

    @Test
    public void testNoArgsConstructor() {
        // Create a Host object using the no-args constructor
        Host host = new Host();

        // Test that the object is not null
        assertNotNull(host);
    }
}
