package com.ubb.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ubb.backend.model.hosts.Host;
import com.ubb.backend.repository.HostRepository;

import java.util.List;
import java.util.Optional;


@Service
public class HostService {
    

    final private HostRepository hostRepository;

     @Autowired
     public HostService(HostRepository HostRepository) {
         this.hostRepository = HostRepository;
     }

    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }

    public Optional<Host> getHostById(Long id) {
        return hostRepository.findById(id);
    }

    public Host createHost(Host Host) {
        return hostRepository.save(Host);
    }

    public Host updateHost(Long id, Host newHostData) {
        return hostRepository.findById(id).map(Host -> {
            Host.setName(newHostData.getName());
            Host.setEmail(newHostData.getEmail());
            Host.setBio(newHostData.getBio());
            Host.setOrganisation(newHostData.getOrganisation());
            Host.setSocialMediaLink(newHostData.getSocialMediaLink());
            return hostRepository.save(Host);
        }).orElse(null);
    }

    public void deleteHost(Long id) {
        hostRepository.deleteById(id);
    }

    public List<Host> getPage(int pageId, boolean isAscending, int pageSize) {
        PageRequest pageable = PageRequest.of(pageId, pageSize);
        Page<Host> page;
        if (isAscending) {
            pageable = pageable.withSort(Sort.by("name").ascending());
        } else {
            pageable = pageable.withSort(Sort.by("name").descending());
        }
        page = this.hostRepository.findAll(pageable);
        return page.getContent();
    }
}
