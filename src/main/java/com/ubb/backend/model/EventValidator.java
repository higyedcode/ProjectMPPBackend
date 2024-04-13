package com.ubb.backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ubb.backend.exceptions.EventValidatorException;

public class EventValidator {

    public static void validate(Event testEvent) throws EventValidatorException
    {
        List<String> errors = new ArrayList<>();
        System.out.println("in validate");
        if(testEvent.getEventName().length() < 3)
            errors.add("Event name too short!");
        
        // if(testEvent.getEventDate().getYear() - LocalDate.now().getYear() <= 0)
        //     errors.add("Event already passed!");
        
        if(testEvent.getEventLocation().length() < 3)
            errors.add("Location invalid");
        
        if (!errors.isEmpty())
            throw new EventValidatorException(errors);
    }
    
}
