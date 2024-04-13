// package com.ubb.backend.websocket;

// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.stereotype.Controller;


// @Controller
// public class WebSocketController {

//     @MessageMapping("/newEvent")
//     @SendTo("/topic/events")
//     public String notifyNewEvent(String payload)
//     {
//         System.out.println("IN CONTROLLER" + payload);
//         return "EVENT ADDED SIGNAL!";
//     }

// }
