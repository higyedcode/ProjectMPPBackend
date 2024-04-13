package com.ubb.backend;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.model.Event;
import com.ubb.backend.repository.EventRepository;
import com.ubb.backend.service.EventService;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    public EventServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEvents() {
        // Arrange
        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(new Event( "Birthday Party",LocalDate.now(), "Here"));
        expectedEvents.add(new Event("Wedding", LocalDate.now(), "There"));
        when(eventRepository.getAll()).thenReturn(expectedEvents);

        // Act
        List<Event> actualEvents = eventService.getAllEvents();

        // Assert
        assertEquals(expectedEvents.size(), actualEvents.size());
        assertEquals(expectedEvents, actualEvents);
    }

    @Test
    public void testGetEventById() throws Exception {
        // Arrange
        Event expectedEvent = new Event("Birthday Party",LocalDate.now(), "Here");
        UUID expectedId = UUID.randomUUID();
        when(eventRepository.getEventById(expectedId)).thenReturn(expectedEvent);

        // Act
        Event actualEvent = eventService.getEventById(expectedId);

        // Assert
        assertEquals(expectedEvent, actualEvent);
    }

    @Test
    public void testAddEvent() throws EventValidatorException {
        // Arrange
        
        Event eventToAdd = new Event("Birthday Party",LocalDate.now(), "Here");

        // Act
        eventService.addEvent(eventToAdd);

        // Assert
        verify(eventRepository, times(1)).add(eventToAdd);
    }

    @Test
    public void testUpdateEvent() throws EventValidatorException {
        // Arrange
        UUID idToUpdate = UUID.randomUUID();
        Event eventToUpdate = new Event("Birthday Party",LocalDate.now(), "Here");

        // Act
        eventService.updateEvent(idToUpdate, eventToUpdate);

        // Assert
        
        verify(eventRepository, times(1)).update(idToUpdate, eventToUpdate);
    }

    @Test
    public void testDeleteEvent() {
        // Arrange
        UUID eventId = UUID.randomUUID();

        // Act
        eventService.deleteEvent(eventId);

        // Assert
        verify(eventRepository, times(1)).deleteById(eventId);
    }
}

