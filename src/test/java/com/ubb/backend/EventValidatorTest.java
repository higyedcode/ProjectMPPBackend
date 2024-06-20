//package com.ubb.backend;
//
//import com.ubb.backend.exceptions.EventValidatorException;
//import com.ubb.backend.model.Role;
//import com.ubb.backend.model.events.Event;
//import com.ubb.backend.model.events.EventValidator;
//import com.ubb.backend.model.hosts.Host;
//import org.junit.jupiter.api.Test;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class EventValidatorTest {
//
//    @Test
//    public void testValidate_ValidEvent() {
//        // Arrange
//        Host host = new Host(0L,"A","pass","mail","","","", Role.USER);
//        Event event = new Event(0L,"Wedding Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
//
//        // Act & Assert
//        assertDoesNotThrow(() -> EventValidator.validate(event));
//    }
//
//    @Test
//    public void testValidate_EventNameTooShort() {
//        // Arrange
//        Host host = new Host(0L,"A","pass","mail","","","",Role.USER);
//        Event event = new Event(0L,"A", LocalDate.of(2024, 4, 15), "123 Main Street", host);
//
//        // Act & Assert
//        EventValidatorException exception = assertThrows(EventValidatorException.class, () -> EventValidator.validate(event));
//        assertTrue(exception.getMessage().contains("Event name too short!"));
//    }
//
//    @Test
//    public void testValidate_InvalidLocation() {
//        Host host = new Host(0L,"A","pass","mail","","","",Role.USER);
//        Event event = new Event(0L,"Ananananana", LocalDate.of(2024, 4, 15), "A", host);
//
//        // Act & Assert
//        EventValidatorException exception = assertThrows(EventValidatorException.class, () -> EventValidator.validate(event));
//        assertTrue(exception.getMessage().contains("Location invalid"));
//
//    }
//
//
//}
