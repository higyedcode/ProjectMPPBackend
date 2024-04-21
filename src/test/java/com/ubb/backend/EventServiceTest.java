 package com.ubb.backend;
 import com.ubb.backend.model.hosts.Host;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import static org.junit.jupiter.api.Assertions.*;
 import org.mockito.Mock;
 import org.mockito.InjectMocks;
 import org.mockito.MockitoAnnotations;

 import com.ubb.backend.exceptions.EventValidatorException;
 import com.ubb.backend.model.events.Event;
 import com.ubb.backend.repository.EventRepository;
 import com.ubb.backend.service.EventService;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageImpl;
 import org.springframework.messaging.simp.SimpMessagingTemplate;

 import static org.mockito.Mockito.*;

 import java.time.LocalDate;
 import java.util.*;


 public class EventServiceTest {

     @Mock
     private EventRepository eventRepository;

     @Mock
     private SimpMessagingTemplate messagingTemplate;

     private EventService eventService;

     @BeforeEach
     public void setUp() {
         MockitoAnnotations.initMocks(this);
         eventService = new EventService(eventRepository, messagingTemplate);
     }

     @Test
     public void testGetAllEvents() {
         when(eventRepository.findAll()).thenReturn(Collections.emptyList());
         assertEquals(Collections.emptyList(), eventService.getAllEvents());
     }


     @Test
     public void testGetEventById() throws Exception {
         Host host = new Host(0L,"A","mail","","","",new ArrayList<Event>());
         Event event = new Event(0L,"Wedding Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
         Optional<Event> optionalEvent = Optional.of(event);
         when(eventRepository.findById(anyLong())).thenReturn(optionalEvent);
         assertEquals(optionalEvent, eventService.getEventById(0L));
     }

     @Test
     public void testAddEvent() throws EventValidatorException {
         Host host = new Host(0L,"A","mail","","","",new ArrayList<Event>());
         Event event = new Event(0L,"Wedding Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
         eventService.addEvent(event);
         verify(eventRepository, times(1)).save(event);
     }

     @Test
     public void testUpdateEvent() throws EventValidatorException {
         Long eventId = 1L;
         Host host = new Host(0L,"A","mail","","","",new ArrayList<Event>());
         Event eventDetails = new Event(0L,"Wedding Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
         Event existingEvent = new Event(0L,"Wedding", LocalDate.of(2024, 4, 15), "123 Main Street", host);
         when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
         eventService.updateEvent(eventId, eventDetails);
         assertEquals(eventDetails.getEventName(), existingEvent.getEventName());
     }

     @Test
     public void testDeleteEvent() {
         Long eventId = 1L;
         eventService.deleteEvent(eventId);
         verify(eventRepository, times(1)).deleteById(eventId);
     }

     @Test
     public void testGetPage() {
         Page<Event> page = new PageImpl<>(Collections.emptyList());
         when(eventRepository.findAllByHostId(anyLong(), any())).thenReturn(page);
         assertEquals(page.getContent(), eventService.getPage(1L, 0, true, 10));
     }

 }

