package com.ubb.backend.repository;

import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.exceptions.RepositoryException;
import com.ubb.backend.model.Event;
import com.ubb.backend.model.EventValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepository {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    Event[] events_arr = {
        new Event(
            
            "Tech Conference 2024",
            LocalDate.of(2024, 5, 20),
            "Convention Center, San Francisco"
        ),
        new Event(
            
            "Product Launch Expo",
            LocalDate.of(2024, 6, 15),
            "Exhibition Hall, New York City"
        ),
        new Event(
            
            "Annual Charity Gala",
            LocalDate.of(2024, 7, 10),
            "Grand Ballroom, Los Angeles"
        ),
        new Event(
            
            "Netflix Show",
            LocalDate.of(2024, 7, 10),
            "Grand Ballroom, Los Angeles"
        ),
        new Event(
            
            "Tech Expo",
            LocalDate.of(2024, 8, 5),
            "Convention Center, San Francisco"
        ),
        new Event(
            
            "Fashion Week",
            LocalDate.of(2024, 9, 20),
            "Fashion Center, Paris"
        ),
        new Event(
            
            "Music Festival",
            LocalDate.of(2024, 9, 25),
            "Outdoor Arena, London"
        ),
        new Event(
            
            "Art Exhibition",
            LocalDate.of(2024, 10, 10),
            "Art Gallery, Rome"
        ),
        new Event(
            
            "Film Premiere",
            LocalDate.of(2024, 10, 15),
            "Red Carpet, Hollywood"
        ),
        new Event(
            
            "Book Fair",
            LocalDate.of(2024, 11, 5),
            "Convention Center, New York City"
        ),
        new Event(
            
            "Food Festival",
            LocalDate.of(2024, 11, 15),
            "Food Hall, Tokyo"
        ),
        new Event(
            
            "Science Expo",
            LocalDate.of(2024, 12, 10),
            "Science Museum, Berlin"
        ),
        new Event(
            
            "Car Show",
            LocalDate.of(2025, 1, 15),
            "Auto Showroom, Detroit"
        ),
        new Event(
            
            "Tech Summit",
            LocalDate.of(2025, 2, 20),
            "Convention Center, San Francisco"
        ),
        new Event(
            
            "Gaming Convention",
            LocalDate.of(2025, 3, 10),
            "Gaming Arena, Seoul"
        ),
        new Event(
            
            "Comedy Show",
            LocalDate.of(2025, 4, 5),
            "Comedy Club, London"
        ),
        new Event(
            
            "Wine Tasting",
            LocalDate.of(2025, 5, 15),
            "Vineyard, Bordeaux"
        ),
        new Event(
            
            "Music Concert",
            LocalDate.of(2025, 6, 20),
            "Concert Hall, Sydney"
        ),
        new Event(
            
            "Fashion Show",
            LocalDate.of(2025, 7, 10),
            "Fashion Center, Milan"
        ),
        new Event(
            "Annual Gala Dinner",
            LocalDate.of(2025, 8, 15),
            "Luxury Hotel, Dubai"
        )
    };

    private final Random random = new Random();
    private final List<String> eventNames = Arrays.asList(
        "Conference on Technology Trends",
        "Music Festival: Summer Vibes",
        "Art Exhibition: Modern Perspectives",
        "Business Networking Mixer",
        "Charity Gala Dinner",
        "Yoga and Meditation Retreat",
        "Movie Night: Classic Films Marathon",
        "Science Fair for Kids",
        "Culinary Workshop: International Cuisine",
        "Fashion Show: Spring Collection",
        "Book Club Meeting: Bestsellers Discussion",
        "Fitness Bootcamp in the Park",
        "Poetry Slam Night",
        "Wine Tasting Event: Discover New Flavors",
        "Career Development Workshop: Resume Writing Tips",
        "Nature Hike: Explore the Wilderness",
        "Tech Startup Pitch Competition",
        "Dance Party: Latin Beats",
        "Photography Workshop: Capturing Landscapes",
        "Comedy Show: Stand-Up Special"
);

private final List<String> locations = Arrays.asList(
        "New York City, USA",
        "Paris, France",
        "London, UK",
        "Tokyo, Japan",
        "Sydney, Australia",
        "Rio de Janeiro, Brazil",
        "Cape Town, South Africa",
        "Dubai, UAE",
        "Rome, Italy",
        "Barcelona, Spain",
        "Mumbai, India",
        "Toronto, Canada",
        "Berlin, Germany",
        "Moscow, Russia",
        "Buenos Aires, Argentina",
        "Seoul, South Korea",
        "Amsterdam, Netherlands",
        "Istanbul, Turkey",
        "Bangkok, Thailand",
        "Cairo, Egypt"
);

private final List<LocalDate> allDates = Arrays.asList(
    LocalDate.of(2024, 5, 20),
    LocalDate.of(2024, 6, 15),
    LocalDate.of(2024, 7, 10),
    LocalDate.of(2024, 8, 5),
    LocalDate.of(2024, 9, 20),
    LocalDate.of(2024, 9, 25),
    LocalDate.of(2024, 10, 10),
    LocalDate.of(2024, 10, 15),
    LocalDate.of(2024, 11, 5),
    LocalDate.of(2024, 11, 15),
    LocalDate.of(2024, 12, 10),
    LocalDate.of(2025, 1, 15),
    LocalDate.of(2025, 2, 20),
    LocalDate.of(2025, 3, 10),
    LocalDate.of(2025, 4, 5),
    LocalDate.of(2025, 5, 15),
    LocalDate.of(2025, 6, 20),
    LocalDate.of(2025, 7, 10),
    LocalDate.of(2025, 8, 15)
);

@Scheduled(fixedRate = 30000) // Run every 10 seconds (10.000 miliseconds)
public void addNewEvents(){
    int rand_name, rand_loc, rand_date;
    
    
    for(int i = 0; i < 1; i++)
    {
        rand_name = this.random.nextInt(this.eventNames.size());
        rand_loc = this.random.nextInt(this.locations.size());
        rand_date = this.random.nextInt(this.allDates.size());
        Event newEvent = new Event(eventNames.get(rand_name), allDates.get(rand_date), locations.get(rand_loc));
        
        try{
            this.add(newEvent);
            System.out.println("New event added: " + newEvent.toString());
            messagingTemplate.convertAndSend("/topic/events","NEW EVENT SIGNAL");
            System.out.println("Message sent: " + newEvent.toString());
            
        }
        catch(EventValidatorException err)
        {
            System.out.println("Error " + err.getMessage());
        }
        
        
    }
    


}


    public static int idCounter = 21;

    public static void incrementCounter()
    {
        idCounter++;
    }

    private List<Event> events = new ArrayList<>(Arrays.asList(events_arr));

    
    public List<Event> getAll() {
        return events;
    }

    public Event getEventById(UUID id) throws Exception
    {
        // System.out.println("ID----: " + id);
        // for (Event ev : events) {
        //     System.out.println(ev.getEventId());
        // }
        
        Optional<Event> eventFound = events.stream().filter(event -> event.getEventId().equals(id)).findFirst();

        
        
        if(eventFound.isEmpty())
            {
        
                
                throw new RepositoryException("Event not found!");
            }
        
            return eventFound.get();
    }

    public void add(Event event) throws EventValidatorException {

        

        EventValidator.validate(event);
        events.add(event);
        System.out.println("Success ADD");
        
    }

    public void deleteById(UUID eventId) {
        
        events.removeIf(e -> e.getEventId().equals(eventId));
    }

    public void update(UUID eventId, Event updatedEvent) throws EventValidatorException {
        EventValidator.validate(updatedEvent);
        Optional<Event> eventToUpdate = events.stream().filter(event -> event.getEventId().equals(eventId)).findFirst();
        

        if(eventToUpdate.isPresent())
        {
            
            eventToUpdate.get().setEventName(updatedEvent.getEventName());
            eventToUpdate.get().setEventDate(updatedEvent.getEventDate());
            eventToUpdate.get().setEventLocation(updatedEvent.getEventLocation());
        }
    }

    public List<Event> sortEvents(boolean isAscending) {

        Collections.sort(events, (e1, e2) -> (int)e1.getEventDate().toEpochDay() - (int)e2.getEventDate().toEpochDay());
        
        if (!isAscending)
            Collections.reverse(events);
        
            return events;
     }

    public List<Event> getPage(int pageId, boolean isAscending, int pageSize) {
       
        sortEvents(isAscending);

    //    int start = Math.min(pageId * pageSize, events.size());
       int end = Math.min((pageId+1) * pageSize, events.size());
       
       return events.subList(0, end);
    }
}
