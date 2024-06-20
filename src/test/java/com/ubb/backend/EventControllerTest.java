//package com.ubb.backend;
//import com.ubb.backend.controller.EventController;
//import com.ubb.backend.controller.HostController;
//import com.ubb.backend.exceptions.EventValidatorException;
//import com.ubb.backend.model.Role;
//import com.ubb.backend.model.events.Event;
//import com.ubb.backend.model.hosts.Host;
//import com.ubb.backend.service.EventService;
//import com.ubb.backend.service.HostService;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static com.ubb.backend.Secrets.SECRET_KEY;
//import static com.ubb.backend.service.EventService.getTokenData;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class EventControllerTest {
//
//    @Mock
//    private EventService eventService;
//    @Mock
//    private HostService hostService;
//
//    private EventController eventController;
//
//    private String token;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        eventController = new EventController(eventService, hostService);
//        token = Jwts.builder()
//                .claim("hostId", 1L)
//                .claim("email", "testmail")
//                .claim("role", Role.ADMIN)
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    @Test
//    public void testGetAllEvents() {
//        List<Event> events = Collections.emptyList();
//        when(eventService.getAllEventsByHostId(1L)).thenReturn(new PageImpl<>(events));
//        assertEquals(events, eventController.getAllEvents(token, 1L).getBody());
//    }
//
//    @Test
//    public void testGetEventById() throws Exception {
//        Long eventId = 1L;
//        Host host = new Host(0L,"A","pass","mail","","","", Role.USER);
//        Event event = new Event(0L,"Wedding Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
//        when(eventService.getEventById(eventId)).thenReturn(Optional.of(event));
//        ResponseEntity<Event> responseEntity = eventController.getEventById(eventId, token);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(event, responseEntity.getBody());
//    }
//
//    @Test
//    public void testGetPage() {
//        Long hostId = 1L;
//        int pageId = 0;
//        boolean isAscending = true;
//        int pageSize = 10;
//        List<Event> events = Collections.emptyList();
//        when(eventService.getPage(hostId, pageId, isAscending, pageSize)).thenReturn(events);
//        assertEquals(events, eventController.getPage(token, pageId, isAscending, pageSize, 0L).getBody());
//    }
//
//    @Test
//    public void testAddEvent() throws EventValidatorException {
//        Host host = new Host(0L,"A","pass","mail","","","",Role.USER);
//        Event event = new Event(0L,"Wedding Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
//        ResponseEntity<String> responseEntity = eventController.addEvent(event,0L, token);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Event added successfully", responseEntity.getBody());
//        verify(eventService, times(1)).addEvent(event, 0L);
//    }
//
//    @Test
//    public void testUpdateEvent() throws EventValidatorException {
//        Long eventId = 0L;
//        Host host = new Host(0L,"A","pass","mail","","","",Role.USER);
//        Event event = new Event(0L,"Wedding Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
//        ResponseEntity<String> responseEntity = eventController.updateEvent(eventId, event, token);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Event updated successfully!", responseEntity.getBody());
//        verify(eventService, times(1)).updateEvent(eventId, event);
//    }
//
//    @Test
//    public void testDeleteEvent() {
//        Long eventId = 0L;
//        eventController.deleteEvent(eventId, token);
//        verify(eventService, times(1)).deleteEvent(eventId);
//    }
//
//    @Test
//    public void testPing() {
//        ResponseEntity<String> responseEntity = eventController.ping();
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Ping successful!", responseEntity.getBody());
//    }
//
//
//
//}
