package com.ubb.backend.service;

import com.ubb.backend.model.events.Event;
import com.ubb.backend.model.hosts.Host;
import com.ubb.backend.repository.EventRepository;
import com.ubb.backend.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private HostRepository hostRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EmailService emailService;


    public List<Event> getEventsOfToday() {
        LocalDate now = LocalDate.now();
//        LocalDate today = LocalDate.now();
        LocalDate today = LocalDate.of(2030,10,10);
//        return notificationRepository.findNotificationsForNextDay(startOfNextDay, endOfNextDay);
        List<Event> events = eventRepository.findAll();
        return events.stream().filter((e) -> e.getEventDate().equals(today)).collect(Collectors.toList());
    }


    @Scheduled(fixedRate = 6000000) // Check every minute
    public void checkNotifications() {
        System.out.println("CHECKING...");
//        List<Event> eventsOfToday = getEventsOfToday();
//        for (Event event : eventsOfToday) {
//            //event.getEventName()
//            emailService.sendEmail(event.getHost().getEmail(), "You have an event coming up!",messageTemplate );
//        }
    }
}
