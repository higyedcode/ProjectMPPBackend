// package com.ubb.backend;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.ubb.backend.controller.EventController;
// import com.ubb.backend.exceptions.EventValidatorException;
// import com.ubb.backend.exceptions.RepositoryException;
// import com.ubb.backend.model.Event;
// import com.ubb.backend.model.EventValidator;
// import com.ubb.backend.service.EventService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import java.time.LocalDate;
// import java.util.Arrays;
// import java.util.List;
// import java.util.UUID;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(EventController.class)
// public class EventControllerTest {

//     @Autowired private ObjectMapper objectMapper;
//     @Autowired private MockMvc mockMvc;
//     @MockBean private EventService eventService;


//     @Test
//     public void testGetAllEvents() throws Exception {
//         // Arrange
//         Event ev1 = new Event("Birthday Party",LocalDate.now(), "Here");
//         Event ev2 = new Event("Wedding", LocalDate.now(), "There");
//         List<Event> events = Arrays.asList(ev1, ev2);
        
//         when(eventService.getAllEvents()).thenReturn(events);

//         // Act & Assert
//         mockMvc.perform(get("/api/events"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$").isArray())
//                 .andExpect(jsonPath("$[0].eventId").value(ev1.getEventId()))
//                 .andExpect(jsonPath("$[0].eventName").value("Birthday Party"))
//                 .andExpect(jsonPath("$[1].eventId").value(ev2.getEventId()))
//                 .andExpect(jsonPath("$[1].eventName").value("Wedding"));
//     }

//     @Test
//     public void testGetEventById() throws Exception
//     {
//         Event event = new Event("Birthday Party",LocalDate.now(), "Here");
        
//         when(eventService.getEventById(event.getEventId())).thenReturn(event);

//         mockMvc.perform(get("/api/events/1"))
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.eventId").value(1))
//         .andExpect(jsonPath("$.eventName").value("Birthday Party"));

//     }

//     @Test
//     public void testGetEventByIdFail() throws Exception
//     {
//        UUID eventId = UUID.randomUUID();
        
//         when(eventService.getEventById(eventId)).thenThrow(new RepositoryException("Event not found!"));

//         mockMvc.perform(get("/api/events/" + eventId))
//         .andExpect(status().isNotFound());
        

//     }

//     @Test
//     public void testAddEvent() throws Exception{
//         Event testEvent = new Event("Birthday Party",LocalDate.now(), "Here");

//         mockMvc.perform(post("/api/events")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(testEvent)))
//                         .andExpect(status().isOk())
//                         .andReturn();
        
//     }
//     @Test
//     public void testAddEventFail() throws Exception {
//         // Arrange
//         Event testEvent = new Event("B", LocalDate.now(), "H");
    
//         // Mock behavior of EventService to throw EventValidatorException
//         doThrow(EventValidatorException.class).when(eventService).addEvent(testEvent);
    
//         // Act & Assert
//         mockMvc.perform(post("/api/events")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(testEvent)))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(content().string("Invalid event data!"));
//     }

//     @Test
//     public void testUpdateEvent() throws Exception{
//         Event testEvent = new Event("Birthday Party",LocalDate.now(), "Here");

//         mockMvc.perform(patch("/api/events/1")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(testEvent)))
//                         .andExpect(status().isOk())
//                         .andReturn();
        
//     }

//     @Test
//     public void testUpdateEventFail() throws Exception{
//         Event testEvent = new Event("Birthday Party",LocalDate.now(), "Here");

//         doThrow(EventValidatorException.class).when(eventService).updateEvent(UUID.randomUUID(), testEvent);


//         mockMvc.perform(patch("/api/events/1")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(testEvent)))
//                         .andExpect(status().isBadRequest())
//                         .andExpect(content().string("Invalid event data!"));
                        
        
//     }
//     @Test
//     public void testDeleteEvent() throws Exception{
      

//         mockMvc.perform(delete("/api/events/1"))
//                         .andExpect(status().isOk())
//                         .andReturn();
        
//     }


// }