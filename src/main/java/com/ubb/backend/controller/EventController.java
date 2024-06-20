package com.ubb.backend.controller;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.ubb.backend.exceptions.RepositoryException;
import com.ubb.backend.model.LocationResponse;
import com.ubb.backend.model.RealLocation;
import com.ubb.backend.model.RealisticEvents;
import com.ubb.backend.model.Role;
import com.ubb.backend.model.events.Event;
import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.model.hosts.Host;
import com.ubb.backend.service.EmailService;
import com.ubb.backend.service.EventService;
import com.ubb.backend.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.ubb.backend.Secrets.SECRET_KEY;
import static com.ubb.backend.service.EventService.getTokenData;
import static java.io.Console.*;
import io.jsonwebtoken.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/events")
public class EventController {


    private final EventService eventService;
    private final HostService hostService;
    private final EmailService emailService;

    @Autowired
    public EventController(EventService eventService, HostService hostService, EmailService emailService) {

        this.eventService = eventService;
        this.hostService = hostService;
//        try{
////            generateFakeEvents();
////            eventService.setCoordinates();
//
//        }
//        catch(Exception e)
//        {
//            System.out.println("ERROR ADDING FAKE EVENTS: " + e.getMessage());
//        }

        this.emailService = emailService;
    }
    public static String getSHA256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Event generateEvent(Faker faker, Host host) {
        RealisticEvents realisticEvents = new RealisticEvents();
        RealLocation realLocations = new RealLocation();
        Event event = new Event();
        event.setHost(host);
        event.setEventName(realisticEvents.getEventName());
        event.setEventLocation(realLocations.getLocation());
        event.setEventDate(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return event;
    }

    private void generateFakeEvents() throws EventValidatorException {
        Faker faker = new Faker();
        Random random = new Random();

        // Generate hosts
        List<Host> hosts = generateFakeHosts();


        // Generate events for each host
        for (Host host : hosts) {
            int numEvents = random.nextInt(20) + 15; // Generate between 3 to 8 events ;random.nextInt(6) + 3
            for (int i = 0; i < numEvents; i++) {
                Event event = generateEvent(faker, host);
                eventService.addEvent(event, host.getId());
            }
        }
        System.out.println("ADDED HOSTS!");

    }

    private List<Host> generateFakeHosts() {
        Faker faker = new Faker();

        for (int i = 0; i < 50; i++) {
            Host host = new Host();
            host.setName(faker.name().fullName());
            host.setEmail(faker.internet().emailAddress());
            host.setBio(faker.lorem().paragraph().substring(0, 30));
            host.setOrganisation(faker.company().name());
            host.setSocialMediaLink(faker.internet().url());
            host.setPassword(getSHA256Hash(host.getName()));
            host.setRole(Role.USER);
            hostService.createHost(host);
        }
        //System.out.println("ADDED HOSTS!");
        return hostService.getAllHosts();

    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(@RequestHeader("Authorization") String token, @RequestParam(value = "hostId", required = false) Long hostId) {
        try {
            Claims tokenData = getTokenData(token);
            Long eventHostId = tokenData.get("hostId", Long.class);
            Role role = Role.valueOf(tokenData.get("role", String.class));
            if(role == Role.USER && hostId == null)
            {
                return ResponseEntity.ok().body(eventService.getAllEventsByHostId(eventHostId).getContent());
            }
            else if(role == Role.ADMIN || role == Role.MANAGER )
            {
                return ResponseEntity.ok().body(eventService.getAllEventsByHostId(hostId).getContent());
            }
            else{
                throw new Exception("Not authorized!");
            }



        }
        catch (Exception e)
        {
            return ResponseEntity.status(401).build();
        }

    }




//    @GetMapping("/{id}")
//    public ResponseEntity<Event> getEventById(@PathVariable Long id, @RequestHeader("Authorization") String token) throws Exception{
//        try{
//            // System.out.println("Long: " + id);
//            Claims tokenData = getTokenData(token);
//            Long hostId = tokenData.get("hostId", Long.class);
//            Role role = Role.valueOf(tokenData.get("role", String.class));
//            Event ev = eventService.getEventById(id).get();
//            if(ev.getHost().getId().equals(hostId) || role == Role.ADMIN || role == Role.MANAGER)
//            {
//                return ResponseEntity.ok().body(ev);
//            }
//            else{
//                return ResponseEntity.status(401).build();
//            }
//        }
//        catch(RepositoryException e)
//        {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
@GetMapping("/{id}")
public ResponseEntity<Event> getEventById(@PathVariable Long id) throws Exception{
    try{
        Event ev = eventService.getEventById(id).get();
            return ResponseEntity.ok().body(ev);
    }
    catch(RepositoryException e)
    {
        return ResponseEntity.notFound().build();
    }

}
    @GetMapping("/getPage")
    @ResponseBody
    public ResponseEntity<List<Event>> getPage(@RequestHeader("Authorization") String token, @RequestParam("page") int pageId, @RequestParam("isAscending") boolean isAscending, @RequestParam("pageSize") int pageSize, @RequestParam( value="hostId", required = false) Long hostId)
    {
        Claims tokenData = getTokenData(token);
        Long eventHostId = tokenData.get("hostId", Long.class);
        Role role = Role.valueOf(tokenData.get("role", String.class));
        if(role == Role.USER && hostId == null)
        {
            return ResponseEntity.ok().body(eventService.getPage(eventHostId, pageId, isAscending, pageSize));
        }
        else if(role == Role.ADMIN || role == Role.MANAGER )
        {
            return ResponseEntity.ok().body(eventService.getPage(hostId, pageId, isAscending, pageSize));
        }
        else{
            return ResponseEntity.status(401).build();
        }

    }


//    @GetMapping("/eventsSize")
//    public ResponseEntity<Integer> getEventsSize() throws Exception{
//        try{
//
//            return ResponseEntity.ok().body(this.eventService.getAllEvents().size());
//        }
//        catch(Exception e)
//        {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
    @GetMapping("/eventsSize")
    public ResponseEntity<Integer> getEventsSize(@RequestHeader("Authorization") String token, @RequestParam( value="hostId", required = false) Long hostId) throws Exception{
        try{
            Claims tokenData = getTokenData(token);
            Long eventHostId = tokenData.get("hostId", Long.class);
            Role role = Role.valueOf(tokenData.get("role", String.class));
            if(role == Role.ADMIN || role == Role.MANAGER )
            {
                return ResponseEntity.ok().body(this.eventService.getAllEventsByHostId(hostId).getSize());
            }
            return ResponseEntity.ok().body(this.eventService.getAllEventsByHostId(eventHostId).getSize());
        }
        catch(Exception e)
        {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/askAI")
    public ResponseEntity<String> getEventsSize( @RequestBody String text) throws Exception{
        try{
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyDfq2y88xb-i5u67S6dbn2KOUavreZZpjk";
            String requestBody = "{\"contents\":[{\"parts\":[{\"text\":\"" + text + "\"}]}]}";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            return ResponseEntity.ok().body(responseEntity.getBody());

        }
        catch(Exception e)
        {
            return ResponseEntity.ok().body(e.toString());
        }

    }



    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody Event event,@RequestParam(value="hostId", required = false) Long hostId, @RequestHeader("Authorization") String token)
    {
        try{
            Claims tokenData = getTokenData(token);
            Long eventHostId = tokenData.get("hostId", Long.class);
            Role role = Role.valueOf(tokenData.get("role", String.class));
            if(role == Role.ADMIN || role == Role.MANAGER)
            {
                eventService.addEvent(event, hostId);
                return ResponseEntity.ok().body("Event added successfully");
            }
            else if(hostId == null)
            {
                eventService.addEvent(event, eventHostId);
                return ResponseEntity.ok().body("Event added successfully");
            }
            else{
                return ResponseEntity.status(401).build();
            }

        }
        catch (EventValidatorException e)
        {
            return ResponseEntity.badRequest().body("Invalid event data!");
        }
        
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody Event event, @RequestHeader("Authorization") String token)
    {
        try{
            Claims tokenData = getTokenData(token);
            Role role = Role.valueOf(tokenData.get("role", String.class));
            if(role != Role.ADMIN && role != Role.MANAGER)
            {
                return ResponseEntity.status(401).build();
            }
            eventService.updateEvent(id, event);
            return ResponseEntity.ok().body("Event updated successfully!");
        }
        catch(EventValidatorException e)
        {
            return ResponseEntity.badRequest().body("Invalid event data!");
        }
        
    }

    @PostMapping("/coordinates/{id}")
    public ResponseEntity<String> addCoordinates(@PathVariable Long id, @RequestBody LocationResponse location, @RequestHeader("Authorization") String token)
    {
        try{
            Claims tokenData = getTokenData(token);
            Role role = Role.valueOf(tokenData.get("role", String.class));
            if(role != Role.ADMIN && role != Role.MANAGER)
            {
                return ResponseEntity.status(401).build();
            }
            eventService.setCoordinates(id, Double.parseDouble(location.getLon()), Double.parseDouble(location.getLat()));
            return ResponseEntity.ok().body("Coordinates added successfully!");
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("Invalid data!");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id, @RequestHeader("Authorization") String token)
    {
        Claims  tokenData = getTokenData(token);
        Role role = Role.valueOf(tokenData.get("role", String.class));
        if(role != Role.ADMIN && role != Role.MANAGER)
        {
            return ResponseEntity.status(401).build();
        }
        eventService.deleteEvent(id);
        return ResponseEntity.ok().body("Event deleted successfully!");
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping()
    {
        return ResponseEntity.ok().body("Ping successful!");
    }

    @PostMapping("/sendInvite/{id}")
    public ResponseEntity<String> sendInvite(@RequestBody Event event,@PathVariable Long id,  @RequestHeader("Authorization") String token)
    {
        Claims  tokenData = getTokenData(token);
        Host host = hostService.getHostById(tokenData.get("hostId", Long.class)).get();
        Host user = hostService.getHostById(id).get();
        emailService.sendEmail(host,user,  host.getName() + " invited you to an event!", event, "Invite");
        return ResponseEntity.ok().body("Invite send successfully!");

    }
    @PostMapping("/sendTicket/{id}")
    public ResponseEntity<String> sendTicket(@RequestBody Event event,@PathVariable Long id,  @RequestHeader("Authorization") String token)
    {
        Claims  tokenData = getTokenData(token);
        Host host = hostService.getHostById(tokenData.get("hostId", Long.class)).get();
        Host user = hostService.getHostById(id).get();
        emailService.sendEmail(host,user,  "Tickets to " + event.getEventName(), event, "Ticket");
        return ResponseEntity.ok().body("Invite send successfully!");

    }

}
