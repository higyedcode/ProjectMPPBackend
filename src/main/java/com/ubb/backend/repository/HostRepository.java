package com.ubb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubb.backend.model.hosts.Host;


public interface HostRepository extends JpaRepository<Host, Long>{
    
}
