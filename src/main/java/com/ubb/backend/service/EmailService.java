package com.ubb.backend.service;

import com.ubb.backend.model.events.Event;
import com.ubb.backend.model.hosts.Host;
import com.ubb.backend.repository.EventRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private JavaMailSender mailSender;

    public EmailService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void sendEmail(Host host, Host user, String subject, Event event, String type) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        message.setFrom("planahead04@example.com");
//        mailSender.send(message);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        Event fullEvent =eventRepository.findAll().stream().filter(e -> e.getEventName().equals(event.getEventName())).findFirst().get();
        String messageTemplate = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        @import url('https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300..800;1,300..800&family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap');\n" +
                "        body { font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f4f4f4;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            height: 100vh;}\n" +
                "        .container {  background-color: #2196f3;\n" +
                "            width: 600px;\n" +
                "            border: 2px solid #ddd;\n" +
                "            border-radius: 10px;\n" +
                "            padding: 20px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}\n" +
                "        .header {  text-align: center;\n" +
                "            margin-bottom: 20px;}\n" +
                "            .header h1{\n" +
                "                margin: 2rem 0 4rem 0;\n" +
                "                color: #002435;\n" +
                "            }\n" +
                "            .content p{\n" +
                "                margin: 5px 0;\n" +
                "            font-size: 20px;\n" +
                "            color: white;\n" +
                "            }\n" +
                "        .content { padding: 50px 20px; background: #1b89e4;margin-top: 20px; border-radius: 10px; color: white; text-align: center; font-size: large;}\n" +
                "        .footer {    text-align: center;\n" +
                "            font-size: 14px;\n" +
                "            color: black;\n" +
                "            margin-top:3rem;\n" +
                "        }\n" +
                "        .button { background-color: #f4f4f4; color: black; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-bottom: 10px; }\n" +
                "        \n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <table role=\"presentation\"  cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <div class=\"container\">\n" +
                "                    <div class=\"header\">\n" +
                "                        <h1>PlanAhead</h1>\n" +
                "                    </div>\n" +
                "                    <div class=\"content\">\n" +
                "                        <p>Hello, "+user.getName()+"</p>\n" +
                "                        <p>Welcome to PlanAhead!</p>\n" +
                "                        <p> "+host.getName()+" invited you to "+event.getEventName()+".</p>\n" +
                "                        <p>"+event.getEventName()+"</p>\n" +
                "                        <p>"+event.getEventDate()+"</p>\n" +
                "                        <p>"+event.getEventLocation()+"</p>\n" +
                "                        <p>Hope to see you there!</p>\n" +
                "                        <br>\n" +
                "                        <br>\n" +
                "                        <br>\n" +
                "                        <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                "                            <tr>\n" +
                "                                <td align=\"center\">\n" +
                "                                    <a href=\"https://toplanahead.onrender.com/checkout?hostId="+user.getId()+"&eventId="+fullEvent.getId()+"\" class=\"button\">Buy tickets from here</a>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>                        \n" +
                "                        <br>\n" +
                "                    </div>\n" +
                "                    <div class=\"footer\">\n" +
                "                        <p>&copy; 2024 PlanAhead. All rights reserved.</p>\n" +
                "                        <p><a href=\"#\">Unsubscribe</a></p>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>\n";

        String ticketTemplate = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Event Ticket</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f4f4f4;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            height: 100vh;\n" +
                "        }\n" +
                "        .ticket-container {\n" +
                "            background-color: white;\n" +
                "            width: 600px;\n" +
                "            border: 2px solid #ddd;\n" +
                "            border-radius: 10px;\n" +
                "            padding: 20px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .ticket-header {\n" +
                "            text-align: center;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .ticket-header h1 {\n" +
                "            margin: 0;\n" +
                "            color: #4CAF50;\n" +
                "        }\n" +
                "        .ticket-details {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .ticket-details h2 {\n" +
                "            margin: 0 0 10px;\n" +
                "            font-size: 20px;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        .ticket-details p {\n" +
                "            margin: 5px 0;\n" +
                "            font-size: 16px;\n" +
                "            color: #555;\n" +
                "        }\n" +
                "        .qr-code {\n" +
                "            text-align: center;\n" +
                "            margin: 20px 0;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            font-size: 12px;\n" +
                "            color: #888;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"ticket-container\">\n" +
                "        <div class=\"ticket-header\">\n" +
                "            <h1>"+event.getEventName()+" Ticket</h1>\n" +
                "            <p>Thank you for your purchase!</p>\n" +
                "        </div>\n" +
                "        <div class=\"ticket-details\">\n" +
                "            <h2>Event Details</h2>\n" +
                "            <p><strong>Event Name:</strong> "+event.getEventName()+"</p>\n" +
                "            <p><strong>Date:</strong> "+event.getEventDate()+"</p>\n" +
                "            <p><strong>Time:</strong> 7:00 PM</p>\n" +
                "            <p><strong>Location:</strong> "+event.getEventLocation()+"</p>\n" +
                "        </div>\n" +
                "        <div class=\"ticket-details\">\n" +
                "            <h2>Attendee Details</h2>\n" +
                "            <p><strong>Name:</strong> "+user.getName()+"</p>\n" +
                "            <p><strong>Email:</strong> "+user.getEmail()+"</p>\n" +
                "        </div>\n" +
                "        <div class=\"qr-code\">\n" +
                "            <img src=\"cid:eventImage\" alt=\"QR Code\" width=\"300px\">\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 PlanAhead. All rights reserved.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";



        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            if(type.equals("Ticket"))
            {
                helper.setText(ticketTemplate, true); // `true` indicates that the text is HTML
                ClassPathResource image = new ClassPathResource("planAhead.jpeg");
                helper.addInline("eventImage", image);
            }
            else{
                helper.setText(messageTemplate, true); // `true` indicates that the text is HTML

            }

            helper.setFrom("planahead04@example.com");
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
