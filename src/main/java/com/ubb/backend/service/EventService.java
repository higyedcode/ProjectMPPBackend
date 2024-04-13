package com.ubb.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.backend.repository.EventRepository;
import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.model.Event;


import java.util.List;
import java.util.UUID;


@Service
public class EventService {

    
    @Autowired
    private EventRepository eventRepo;

    public List<Event> getAllEvents()
    {
        return eventRepo.getAll();
    }

    public Event getEventById(UUID id) throws Exception
    {
        return eventRepo.getEventById(id);
    }

    public void addEvent(Event event) throws EventValidatorException
    {
        eventRepo.add(event);
    }
    public void updateEvent(UUID id, Event event) throws EventValidatorException{
       eventRepo.update(id, event);
    }
    public void deleteEvent(UUID id)
    {
        eventRepo.deleteById(id);
    }

    public List<Event> getPage(int pageId, boolean isAscending, int pageSize) {
        return this.eventRepo.getPage(pageId, isAscending, pageSize);
    }

}
