package com.ubb.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.model.Event;
import com.ubb.backend.repository.EventRepository;




public class EventRepositoryTest {
    
    @Test
    public void testGetAll() {
        EventRepository eventRepository = new EventRepository();
        assertEquals(20, eventRepository.getAll().size());
    }
    @Test
    public void testGetEventById() throws Exception {
        EventRepository eventRepository = new EventRepository();
        Event event = eventRepository.getEventById(eventRepository.getAll().get(0).getEventId()); 
        assertEquals("Tech Conference 2024", event.getEventName());
    }
    @Test
    public void testAdd() throws EventValidatorException {
        EventRepository eventRepository = new EventRepository();
        eventRepository.add(new Event( "NewEvent", LocalDate.now(), "NewLocation")); // Add a new event
        assertEquals(21, eventRepository.getAll().size()); // Verify that the event was added
    }
    @Test
    public void testDeleteById() {
        EventRepository eventRepository = new EventRepository();
        eventRepository.deleteById(eventRepository.getAll().get(0).getEventId()); 
        assertEquals(19, eventRepository.getAll().size());
    }
    @Test
    public void testUpdate() throws Exception {
        EventRepository eventRepository = new EventRepository();
        UUID replacedEventId = eventRepository.getAll().get(0).getEventId();
        Event updatedEvent = new Event("UpdatedEventName",LocalDate.now(), "bbb"); // Create an updated event
        eventRepository.update(replacedEventId, updatedEvent); // Update event with ID 1
        assertEquals("UpdatedEventName", eventRepository.getEventById(replacedEventId).getEventName()); // Verify that the event was updated
    }
}
