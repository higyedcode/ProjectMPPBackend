package com.ubb.backend.controller;

import com.ubb.backend.exceptions.RepositoryException;
import com.ubb.backend.model.events.Event;
import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.model.hosts.Host;
import com.ubb.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.io.Console.*;


@RestController
@RequestMapping("/api/events")
public class EventController {
    

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents(@RequestParam(required = false) Long hostId)
    {
        if (hostId != null)
            return eventService.getAllEventsByHostId(hostId).getContent();
        else
            return eventService.getAllEvents();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) throws Exception{
        try{
            // System.out.println("Long: " + id);
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
    public List<Event> getPage(@RequestParam("hostId") Long hostId,@RequestParam("page") int pageId, @RequestParam("isAscending") boolean isAscending, @RequestParam("pageSize") int pageSize)
    {
        return this.eventService.getPage(hostId, pageId, isAscending, pageSize);
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
    public ResponseEntity<Integer> getEventsSize(@RequestParam("hostId") Long hostId) throws Exception{
        try{

            return ResponseEntity.ok().body(this.eventService.getAllEventsByHostId(hostId).getSize());
        }
        catch(Exception e)
        {
            return ResponseEntity.notFound().build();
        }

    }



    @PostMapping("/{hostId}")
    public ResponseEntity<String> addEvent(@RequestBody Event event, @PathVariable Long hostId)
    {
        try{

            eventService.addEvent(event, hostId);
            return ResponseEntity.ok().body("Event added successfully");
        }
        catch (EventValidatorException e)
        {
            return ResponseEntity.badRequest().body("Invalid event data!");
        }
        
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody Event event)
    {
        try{
            eventService.updateEvent(id, event);
            return ResponseEntity.ok().body("Event updated successfully!");
        }
        catch(EventValidatorException e)
        {
            return ResponseEntity.badRequest().body("Invalid event data!");
        }
        
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id)
    {
        eventService.deleteEvent(id);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping()
    {
        return ResponseEntity.ok().body("Ping successful!");
    }

}
