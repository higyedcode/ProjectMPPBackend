package com.ubb.backend.controller;

import com.ubb.backend.exceptions.RepositoryException;
import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.model.Event;
import com.ubb.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/events")

public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents()
    {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable UUID id) throws Exception{
        try{
            // System.out.println("UUID: " + id);
            Event ev = eventService.getEventById(id);
            return ResponseEntity.ok().body(ev);
        }
        catch(RepositoryException e)
        {
            return ResponseEntity.notFound().build();
        }
        
    }

    @GetMapping("/eventsSize")
    public ResponseEntity<Integer> getEventsSize() throws Exception{
        try{
            
            return ResponseEntity.ok().body(this.eventService.getAllEvents().size());
        }
        catch(Exception e)
        {
            return ResponseEntity.notFound().build();
        }
        
    }

    @GetMapping("/getPage")
    @ResponseBody
    public List<Event> getPage(@RequestParam("page") int pageId, @RequestParam("isAscending") boolean isAscending, @RequestParam("pageSize") int pageSize)
    {
        return this.eventService.getPage(pageId, isAscending, pageSize);
    }


    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody Event event)
    {
        try{
            
            eventService.addEvent(event);
            return ResponseEntity.ok().body("Event added successfully");
        }
        catch (EventValidatorException e)
        {
            return ResponseEntity.badRequest().body("Invalid event data!");
        }
        
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable UUID id, @RequestBody Event event)
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
    public void deleteEvent(@PathVariable UUID id)
    {
        eventService.deleteEvent(id);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping()
    {
        return ResponseEntity.ok().body("Ping successful!");
    }

}
