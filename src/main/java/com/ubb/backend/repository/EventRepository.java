package com.ubb.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ubb.backend.model.events.Event;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {
    public Page<Event> findAllByHostId(Long hostId, PageRequest pageable);

    long countByHostId(Long hostId);

    @Query("SELECT COUNT(e) FROM events e WHERE e.host.id = :hostId")
    int getNrEventsOfHost(Long hostId);

}
