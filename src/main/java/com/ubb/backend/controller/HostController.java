package com.ubb.backend.controller;


import com.github.javafaker.Faker;
import com.ubb.backend.model.Role;
import com.ubb.backend.model.events.Event;
import com.ubb.backend.service.EventService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import com.ubb.backend.model.hosts.Host;
import com.ubb.backend.model.hosts.HostDTO;
import com.ubb.backend.service.HostService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ubb.backend.Secrets.SECRET_KEY;
import static com.ubb.backend.controller.EventController.getSHA256Hash;
import static com.ubb.backend.service.EventService.getTokenData;


@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostService hostService;
    private final EventService eventService;


    @Autowired
    public HostController(HostService hostService, EventService eventService){
        this.hostService = hostService;
        System.out.println("SECRET KEY: " + SECRET_KEY);
        this.eventService = eventService;
    }

    @GetMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestParam String email, @RequestParam String password)
    {
        Optional<Host> foundHost = hostService.getHostByEmail(email);
        if(foundHost.isPresent())
        {
            String foundPassword = foundHost.get().getPassword();
            if(getSHA256Hash(password).equals(foundPassword))
            {
                String token = Jwts.builder()
                        .claim("hostId", foundHost.get().getId())
                        .claim("email", foundHost.get().getEmail())
                        .claim("role", foundHost.get().getRole())
                        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                        .compact();
                return ResponseEntity.ok().body(token);
            }

        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/nrEvents")
    public ResponseEntity<Integer> getNrEvents(@RequestParam Long hostId, @RequestHeader("Authorization") String token)
    {
        Claims tokenData = getTokenData(token);
        Role role = Role.valueOf(tokenData.get("role", String.class));
        if(role == null)
        {
            //401 = unauthorised
            return ResponseEntity.status(401).build();
        }

//        Optional<Host> foundHost = hostService.getHostById(hostId);
//        if(foundHost.isPresent())
//        {
//
//            return ResponseEntity.ok().body(foundHost.get().getEvents().size());
//        }

//        return ResponseEntity.status(401).build();
        return ResponseEntity.ok().body(eventService.getNrEventsOfHost(hostId));
    }

    @GetMapping
    public ResponseEntity<List<Host>> getAllHosts(@RequestHeader("Authorization") String token){
        try{
            Claims tokenData = getTokenData(token);
            Role role = Role.valueOf(tokenData.get("role", String.class));

            return ResponseEntity.ok().body(hostService.getAllHosts());
        }
        catch(Exception ex){
            return ResponseEntity.status(401).build();
        }



    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getHostById(@PathVariable Long id, @RequestHeader("Authorization") String token){
        try {
            Claims tokenData = getTokenData(token);
            Role role = Role.valueOf(tokenData.get("role", String.class));
            if(role == null)
            {
                //401 = unauthorised
                return ResponseEntity.status(401).build();
            }
            Host Host = hostService.getHostById(id).get();
            return ResponseEntity.ok().body(Host);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hostsSize")
    public ResponseEntity<Integer> getHostSize(@RequestHeader("Authorization") String token) throws Exception{
        try{
            Claims tokenData = getTokenData(token);
            try {
                Role role = Role.valueOf(tokenData.get("role", String.class));
            }
            catch (Exception e)
            {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok().body(this.hostService.getAllHosts().size());
        }
        catch(Exception e)
        {
            return ResponseEntity.notFound().build();
        }
        
    }

    @PostMapping
    public ResponseEntity<Host> addHost(@RequestHeader("Authorization") String token,@RequestBody HostDTO host) {
        try {
            Claims tokenData = getTokenData(token);
            try {
                Role role = Role.valueOf(tokenData.get("role", String.class));
            }
            catch (Exception e)
            {
                return ResponseEntity.status(401).build();
            }
            System.out.println(host.toString());
            // retrieve the host and set its password since it has @JsonIgnore
            Host hostToAdd = host.getHost();
            String hostPassword = host.getPassword();
            hostToAdd.setPassword(getSHA256Hash(hostPassword));


            System.out.println(hostToAdd.toString());
            Host createdHost = hostService.createHost(hostToAdd);
            return ResponseEntity.ok().body(createdHost);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Host> addHost(@RequestBody HostDTO host) {
        try {
            System.out.println(host.toString());
            // retrieve the host and set its password since it has @JsonIgnore
            Host hostToAdd = host.getHost();
            String hostPassword = host.getPassword();
            hostToAdd.setPassword(getSHA256Hash(hostPassword));
            hostToAdd.setRole(Role.USER);


            System.out.println(hostToAdd.toString());
            Host createdHost = hostService.createHost(hostToAdd);
            return ResponseEntity.ok().body(createdHost);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/getPage")
    @ResponseBody
    public ResponseEntity<List<Host>> getPage(@RequestParam("page") int pageId, @RequestParam("isAscending") boolean isAscending, @RequestParam("pageSize") int pageSize, @RequestHeader("Authorization") String token)
    {

        try {
            Claims tokenData = getTokenData(token);
            Role role = Role.valueOf(tokenData.get("role", String.class));
            return ResponseEntity.ok().body(this.hostService.getPage(pageId, isAscending, pageSize));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(401).build();
        }

    }


    @PatchMapping("/{id}")
    public ResponseEntity<Host> updateHost(@PathVariable Long id, @RequestBody Host Host, @RequestHeader("Authorization") String token) {
        try {
            System.out.println(Host);
            Claims tokenData = getTokenData(token);
            Role role = Role.valueOf(tokenData.get("role", String.class));
            if(role != Role.ADMIN && role != Role.MANAGER)
            {
                //401 = unauthorised
                return ResponseEntity.status(401).build();
            }
            Host updatedHost = hostService.updateHost(id, Host);
            if(role == Role.ADMIN)
            {
                hostService.updateHostRole(id, Host.getRole());
            }
            return ResponseEntity.ok().body(updatedHost);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            Claims tokenData = getTokenData(token);
            Role role = Role.valueOf(tokenData.get("role", String.class));
            if(role != Role.ADMIN && role != Role.MANAGER)
            {
                //401 = unauthorised
                return ResponseEntity.status(401).build();
            }
            hostService.deleteHost(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/ping")
    public ResponseEntity<String> ping()
    {
        return ResponseEntity.ok().body("Ping successful!");
    }
    
}
