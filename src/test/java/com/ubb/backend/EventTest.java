// package com.ubb.backend;
// import com.ubb.backend.model.Role;
// import com.ubb.backend.model.events.Event;
// import com.ubb.backend.model.hosts.Host;
// import org.junit.jupiter.api.Test;
//
//
//
// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// public class EventTest {
//
//     @Test
//     public void testGettersAndSetters() {
//         // Create an event object
//         Host host = new Host(0L,"A","pass","mail","","","", Role.USER);
//         Event event = new Event(0L,"Birthday Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
//
//         // Test getters
//         assertEquals(0L, event.getId());
//         assertEquals("Birthday Party", event.getEventName());
//         assertEquals(LocalDate.of(2024, 4, 15), event.getEventDate());
//         assertEquals("123 Main Street", event.getEventLocation());
//
//         // Test setters
//         event.setId(2L);
//         event.setEventName("Wedding");
//         event.setEventDate(LocalDate.of(2024, 5, 20));
//         event.setEventLocation("456 Elm Street");
//
//         assertEquals(2, event.getId());
//         assertEquals("Wedding", event.getEventName());
//         assertEquals(LocalDate.of(2024, 5, 20), event.getEventDate());
//         assertEquals("456 Elm Street", event.getEventLocation());
//     }
//
//     @Test
//     public void testEqualsAndHashCode() {
//         Host host1 = new Host(0L,"A","pass","mail","","","",Role.USER);
//         Event event1 = new Event(0L,"Birthday Party", LocalDate.of(2024, 4, 15), "123 Main Street", host1);
//         Host host2 = new Host(0L,"A","pass","mail","","","",Role.USER);
//         Event event2 = new Event(0L,"Birthday Party", LocalDate.of(2024, 4, 15), "123 Main Street", host2);
//
//         Host host = new Host(0L,"A","pass","mail","","","",Role.USER);
//         Event event = new Event(0L,"Wedding Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
//
//         // Test equals method
//         assertTrue(event1.equals(event2)); // event1 should be equal to event2
//         assertFalse(event1.equals(event)); // event1 should not be equal to event3
//
//         // Test hashCode method
//         assertEquals(event1.hashCode(), event2.hashCode()); // hashCode of event1 should be equal to hashCode of event2
//     }
//
//     @Test
//     public void testToString() {
//         Host host = new Host(0L,"A","pass","mail","","","",Role.USER);
//         Event event = new Event(0L,"Birthday Party", LocalDate.of(2024, 4, 15), "123 Main Street", host);
//         String expectedToString = "Event(id=0, eventName=Birthday Party, eventDate=2024-04-15, eventLocation=123 Main Street, host=Host(id=0, name=A, password=pass, email=mail, bio=, organisation=, socialMediaLink=, role=USER))";
//         assertEquals(expectedToString, event.toString());
//     }
// }
