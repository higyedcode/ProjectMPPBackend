package com.ubb.backend.controller;


import com.github.javafaker.Faker;
import com.ubb.backend.model.events.Event;
import com.ubb.backend.model.hosts.Host;
import com.ubb.backend.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostService hostService;

    @Autowired
    public HostController(HostService hostService){
        this.hostService = hostService;
    }


    @GetMapping
    public List<Host> getAllHosts() {
        
            return hostService.getAllHosts();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getHostById(@PathVariable Long id) {
        try {
            Host Host = hostService.getHostById(id).get();
            return ResponseEntity.ok().body(Host);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hostsSize")
    public ResponseEntity<Integer> getHostSize() throws Exception{
        try{
            
            return ResponseEntity.ok().body(this.hostService.getAllHosts().size());
        }
        catch(Exception e)
        {
            return ResponseEntity.notFound().build();
        }
        
    }

    @PostMapping
    public ResponseEntity<Host> addHost(@RequestBody Host Host) {
        try {
            Host createdHost = hostService.createHost(Host);
            return ResponseEntity.ok().body(createdHost);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/getPage")
    @ResponseBody
    public List<Host> getPage(@RequestParam("page") int pageId, @RequestParam("isAscending") boolean isAscending, @RequestParam("pageSize") int pageSize)
    {
        return this.hostService.getPage(pageId, isAscending, pageSize);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Host> updateHost(@PathVariable Long id, @RequestBody Host Host) {
        try {
            Host updatedHost = hostService.updateHost(id, Host);
            return ResponseEntity.ok().body(updatedHost);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        try {
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
