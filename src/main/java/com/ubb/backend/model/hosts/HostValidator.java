package com.ubb.backend.model.hosts;

import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.model.events.Event;

import java.util.ArrayList;
import java.util.List;

public class HostValidator {
    public static void validate(Host testHost) throws EventValidatorException
    {
        List<String> errors = new ArrayList<>();
        System.out.println("in validate");
       if (testHost.getName().length() <  3)
           errors.add("Host Name too short!");
       if(!testHost.getEmail().contains("@"))
           errors.add("The email doesn't contain the symbol @");

        if (!errors.isEmpty())
            throw new EventValidatorException(errors);
    }
}
