package com.ubb.backend;

import com.ubb.backend.exceptions.EventValidatorException;
import com.ubb.backend.model.events.Event;
import com.ubb.backend.model.hosts.Host;
import com.ubb.backend.model.hosts.HostValidator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HostValidatorTest {

    @Test
    public void testValidate_ValidHost() {
        // Arrange
        Host validHost = new Host(1L,"Anmasnmasnmas","mail@gmail","","","",new ArrayList<Event>());;

        // Act & Assert
        assertDoesNotThrow(() -> HostValidator.validate(validHost));
    }

    @Test
    public void testValidate_HostNameTooShort() {
        // Arrange
        Host host = new Host(1L,"A","mail","","","",new ArrayList<Event>());;

        // Act & Assert
        EventValidatorException exception = assertThrows(EventValidatorException.class, () -> HostValidator.validate(host));
        assertTrue(exception.getMessage().contains("Host Name too short!"));
    }

    @Test
    public void testValidate_InvalidEmail() {
        // Arrange
        Host host = new Host(1L,"Anmasnmasnmas","mail","","","",new ArrayList<Event>());;

        // Act & Assert
        EventValidatorException exception = assertThrows(EventValidatorException.class, () -> HostValidator.validate(host));
        assertTrue(exception.getMessage().contains("The email doesn't contain the symbol @"));
    }

    // Add more test methods as needed
}
