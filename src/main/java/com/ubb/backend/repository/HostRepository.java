package com.ubb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ubb.backend.model.hosts.Host;

import java.util.Optional;


public interface HostRepository extends JpaRepository<Host, Long>{

    Optional<Host> findByEmail(String email);
}
