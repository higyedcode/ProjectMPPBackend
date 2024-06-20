package com.ubb.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubb.backend.model.LocationResponse;
import com.ubb.backend.model.hosts.Host;
import com.ubb.backend.repository.HostRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import com.github.javafaker.Faker;
import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.model.events.Event;
import com.ubb.backend.repository.EventRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.ubb.backend.Secrets.SECRET_KEY;


@Service
public class EventService {

    private final EventRepository eventRepo;
    private final HostRepository hostRepository;

     private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public EventService(EventRepository eventRepository, HostRepository hostRepository, SimpMessagingTemplate messagingTemplate) {
        this.eventRepo = eventRepository;
        this.messagingTemplate = messagingTemplate;
        this.hostRepository = hostRepository;
    }
    
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     private final Faker faker = new Faker();
    

    public List<Event> getAllEvents()
    {
        return eventRepo.findAll();
    }

    public static Claims getTokenData(String token)
    {
        //System.out.println("Token: " + token);
        String finalToken = token.replace("Bearer ", "").trim();


        // Decode JWT token to retrieve claims (e.g., user ID)
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(finalToken).getBody();

    }
    public Page<Event> getAllEventsByHostId(Long hostId)
    {
        PageRequest pageable = PageRequest.of(0, (int)eventRepo.countByHostId(hostId));
        return eventRepo.findAllByHostId(hostId, pageable);
    }

    public Optional<Event> getEventById(Long id) throws Exception
    {
        return eventRepo.findById(id);
    }

    public int getNrEventsOfHost(Long hostId)
    {
        return eventRepo.getNrEventsOfHost(hostId);
    }
    public void addEvent(Event event, Long hostId) throws EventValidatorException
    {
        Optional<Host> host = hostRepository.findById(hostId);
        if(host.isPresent())
        {

            event.setHost(host.get());
        }

        eventRepo.save(event);

    }
    public Event updateEvent(Long eventId, Event eventDetails) throws EventValidatorException{
        Optional<Event> optionalEvent = eventRepo.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            existingEvent.setEventName(eventDetails.getEventName());
            existingEvent.setEventDate(eventDetails.getEventDate());
            existingEvent.setEventLocation(eventDetails.getEventLocation());
            return eventRepo.save(existingEvent);
        } else {
            // Handle not found scenario
            return null;
        }
    }
    public void deleteEvent(Long id)
    {
        eventRepo.deleteById(id);
    }

//    public List<Event> getAllByHostId(Long hostId)
//    {
//        PageRequest pageable = PageRequest.of(0,this)
//       return this.eventRepo.findAllByHostId(hostId);
//    }
    public List<Event> getPage(Long hostId,int pageId, boolean isAscending, int pageSize) {
        PageRequest pageable = PageRequest.of(pageId, pageSize);
        Page<Event> page;
        if (isAscending) {
            pageable = pageable.withSort(Sort.by("eventName").ascending());
        } else {
            pageable = pageable.withSort(Sort.by("eventName").descending());
        }
        page = this.eventRepo.findAllByHostId(hostId, pageable);
        return page.getContent();
    }




public void setCoordinates(Long id, Double lon, Double lat) {
        Event event = eventRepo.findById(id).get();
        event.setLatitude(lat);
        event.setLongitude(lon);

        eventRepo.save(event);

}
    
 @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS) // Run every 5 seconds
 @Async
 public void addNewEvents(){

         String eventName = faker.lorem().word(); // Generate a random event name using Faker
         LocalDate date = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Generate a random date using Faker
         String location = faker.address().city(); // Generate a random location using Faker

         Event newEvent = new Event();
        
//         try{
//             this.addEvent(newEvent);
//             System.out.println("New event added: " + newEvent.toString());
//              messagingTemplate.convertAndSend("/topic/events","NEW EVENT SIGNAL");
//             System.out.println("Message sent: " + newEvent.toString());
            
//         }
//         catch(EventValidatorException err)
//         {
//             System.out.println("Error " + err.getMessage());
//         }
//
        

    


 }


}
