//package com.ubb.backend;
//import com.ubb.backend.controller.HostController;
//import com.ubb.backend.model.Role;
//import com.ubb.backend.model.events.Event;
//import com.ubb.backend.model.hosts.Host;
//import com.ubb.backend.model.hosts.HostDTO;
//import com.ubb.backend.service.EventService;
//import com.ubb.backend.service.HostService;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static com.ubb.backend.Secrets.SECRET_KEY;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class HostControllerTest {
//
//    @Mock
//    private HostService hostService;
//
//    @Mock
//    private EventService eventService;
//
//    private HostController hostController;
//    private String token;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        hostController = new HostController(hostService,eventService);
//        token = Jwts.builder()
//                .claim("hostId", 1L)
//                .claim("email", "testmail")
//                .claim("role", Role.ADMIN)
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    @Test
//    public void testGetAllHosts() {
//        List<Host> hosts = Collections.emptyList();
//        when(hostService.getAllHosts()).thenReturn(hosts);
//        assertEquals(hosts, hostController.getAllHosts(token).getBody());
//    }
//
//    @Test
//    public void testGetHostById() {
//        Long hostId = 1L;
//        Host host =new Host(1L,"A","pass","mail","","","",Role.USER);
//        when(hostService.getHostById(hostId)).thenReturn(Optional.of(host));
//        ResponseEntity<Host> responseEntity = hostController.getHostById(hostId,token);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(host, responseEntity.getBody());
//    }
//
//
//    @Test
//    public void testAddHost() {
//        Host host =new Host(1L,"A","pass","mail","","","",Role.USER);
//        ResponseEntity<Host> responseEntity = hostController.addHost(new HostDTO("A","pass","mail","","",""));
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }
//
//    @Test
//    public void testGetPage() {
//        int pageId = 0;
//        boolean isAscending = true;
//        int pageSize = 10;
//        List<Host> hosts = Collections.emptyList();
//        when(hostService.getPage(pageId, isAscending, pageSize)).thenReturn(hosts);
//        assertEquals(hosts, hostController.getPage(pageId, isAscending, pageSize, token).getBody());
//    }
//
//    @Test
//    public void testUpdateHost() {
//        Long hostId = 1L;
//        Host host =new Host(1L,"A","pass","mail","","","",Role.USER);
//        when(hostService.updateHost(hostId, host)).thenReturn(host);
//        ResponseEntity<Host> responseEntity = hostController.updateHost(hostId, host, token);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(host, responseEntity.getBody());
//    }
//
//    @Test
//    public void testDeleteHost() {
//        Long hostId = 1L;
//        ResponseEntity<Void> responseEntity = hostController.deleteHost(hostId, token);
//        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
//        verify(hostService, times(1)).deleteHost(hostId);
//    }
//
//    @Test
//    public void testPing() {
//        ResponseEntity<String> responseEntity = hostController.ping();
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Ping successful!", responseEntity.getBody());
//    }
//
//
//}
//
