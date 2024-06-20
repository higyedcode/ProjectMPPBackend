//package com.ubb.backend;
//import com.ubb.backend.model.Role;
//import com.ubb.backend.model.hosts.Host;
//import com.ubb.backend.repository.HostRepository;
//import com.ubb.backend.service.HostService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.mockito.Mock;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//
//import com.ubb.backend.exceptions.EventValidatorException;
//import com.ubb.backend.model.events.Event;
//import com.ubb.backend.repository.EventRepository;
//import com.ubb.backend.service.EventService;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//
//import static org.mockito.Mockito.*;
//
//import java.time.LocalDate;
//import java.util.*;
//
//public class HostServiceTest {
//    @Mock
//    private HostRepository hostRepository;
//
//    private HostService hostService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        hostService = new HostService(hostRepository);
//    }
//
//    @Test
//    public void testGetAllHosts() {
//        when(hostRepository.findAll()).thenReturn(Collections.emptyList());
//        assertEquals(Collections.emptyList(), hostService.getAllHosts());
//    }
//
//    @Test
//    public void testGetHostById() {
//        Optional<Host> optionalHost = Optional.of(new Host(0L,"A","pass","mail","","","", Role.USER));
//        when(hostRepository.findById(anyLong())).thenReturn(optionalHost);
//        assertEquals(optionalHost, hostService.getHostById(1L));
//    }
//
//    @Test
//    public void testCreateHost() {
//        Host host = new Host(0L,"A","pass","mail","","","",Role.USER);
//        when(hostRepository.save(any())).thenReturn(host);
//        assertEquals(host, hostService.createHost(host));
//    }
//
//    @Test
//    public void testUpdateHost() {
//        Long hostId = 1L;
//        Host newHostData = new Host(0L,"A","pass","mail","","","",Role.USER);
//        Host existingHost = new Host(0L,"B","pass","mail","","","",Role.USER);
//        when(hostRepository.findById(hostId)).thenReturn(Optional.of(existingHost));
//        when(hostRepository.save(any())).thenReturn(existingHost);
//        assertEquals(existingHost, hostService.updateHost(hostId, newHostData));
//    }
//
//    @Test
//    public void testDeleteHost() {
//        Long hostId = 1L;
//        hostService.deleteHost(hostId);
//        verify(hostRepository, times(1)).deleteById(hostId);
//    }
//
//    @Test
//    public void testGetPage() {
//        Page<Host> page = new PageImpl<>(Collections.emptyList());
//        when(hostRepository.findAll(any(PageRequest.class))).thenReturn(page);
//        assertEquals(page.getContent(), hostService.getPage(0, true, 10));
//    }
//}
