package com.ubb.backend.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RealisticEvents {
    private List<String> events;
    private int eventsLength;
    private Random random;

        public RealisticEvents()
        {
            this.random = new Random();
            events  = Arrays.asList(
            "Summer Olympics",
            "Winter Olympics",
            "FIFA World Cup",
            "UEFA Champions League Final",
            "Super Bowl",
            "Wimbledon",
            "Tour de France",
            "NBA Finals",
            "World Series",
            "Comic-Con",
            "Cannes Film Festival",
            "Academy Awards",
            "Coachella Valley Music and Arts Festival",
            "New York Fashion Week",
            "The Masters Tournament",
            "Glastonbury Festival",
            "SXSW (South by Southwest)",
            "San Diego Comic-Con International",
            "The British Open (Golf)",
            "The Oscars",
            "Bayreuth Festival",
            "Bonnaroo Music and Arts Festival",
            "Berlin International Film Festival (Berlinale)",
            "Newport Jazz Festival",
            "Roskilde Festival",
            "Montreux Jazz Festival",
            "Edinburgh Festival Fringe",
            "Edinburgh International Festival",
            "The Proms",
            "Tomorrowland",
            "Rock in Rio",
            "Reading and Leeds Festivals",
            "Download Festival",
            "Glastonbury Festival",
            "Primavera Sound",
            "Lollapalooza",
            "Electric Daisy Carnival (EDC)",
            "Coachella Valley Music and Arts Festival",
            "Tomorrowland Winter",
            "Tomorrowland Around the World",
            "Sundance Film Festival",
            "Tribeca Film Festival",
            "Toronto International Film Festival (TIFF)",
            "Venice Film Festival",
            "Cannes Lions International Festival of Creativity",
            "Macy's Thanksgiving Day Parade",
            "Rose Parade",
            "Edinburgh Hogmanay",
            "Calgary Stampede",
            "La Tomatina",
            "Rio Carnival",
            "Oktoberfest",
            "Mardi Gras",
            "Running of the Bulls (Pamplona)",
            "Carnival of Venice",
            "New Orleans Jazz & Heritage Festival",
            "Notting Hill Carnival",
            "Up Helly Aa",
            "Carnevale di Ivrea",
            "Burning Man",
            "San Fermin Festival",
            "Saint Patrick's Day Parade (New York)",
            "Hong Kong Dragon Boat Festival",
            "Gion Matsuri",
            "Koningsdag (King's Day, Netherlands)",
            "White Nights Festival (St. Petersburg)",
            "Boryeong Mud Festival",
            "Sapporo Snow Festival",
            "Albuquerque International Balloon Fiesta",
            "La Merce Festival (Barcelona)",
            "Cheung Chau Bun Festival",
            "Harbin Ice Festival",
            "Dia de los Muertos (Day of the Dead, Mexico)",
            "Holi Festival",
            "Diwali Festival",
            "Songkran Festival",
            "Hanami (Cherry Blossom Viewing, Japan)",
            "Gion Matsuri",
            "Carnival of Basel",
            "Venice Carnival",
            "Viareggio Carnival",
            "Semana Santa (Holy Week, Seville)",
            "Las Fallas (Valencia)",
            "Pamplona Bull Run",
            "Feria de Abril (Seville)",
            "Albuquerque International Balloon Fiesta",
            "Pushkar Camel Fair",
            "Oktoberfest",
            "La Tomatina",
            "Running of the Bulls (Pamplona)",
            "Edinburgh Festival Fringe",
            "Edinburgh International Festival",
            "Oktoberfest",
            "Gion Matsuri",
            "Hanami (Cherry Blossom Viewing, Japan)",
            "Up Helly Aa (Shetland)",
            "Sapporo Snow Festival",
            "Mardi Gras (New Orleans)",
            "Rio Carnival",
            "Carnival of Venice",
            "Diwali Festival",
            "Songkran Festival",
            "La Merce Festival (Barcelona)",
            "White Nights Festival (St. Petersburg)",
            "Carnevale di Ivrea",
            "San Fermin Festival (Pamplona)",
            "Notting Hill Carnival",
            "Saint Patrick's Day Parade (New York)",
            "Calgary Stampede",
            "Boryeong Mud Festival",
            "Harbin Ice Festival",
            "Hong Kong Dragon Boat Festival",
            "Cheung Chau Bun Festival",
            "La Tomatina",
            "Dia de los Muertos (Day of the Dead, Mexico)",
            "Holi Festival",
            "Glastonbury Festival",
            "Coachella Valley Music and Arts Festival",
            "Tomorrowland",
            "Primavera Sound",
            "Lollapalooza",
            "Electric Daisy Carnival (EDC)",
            "Reading and Leeds Festivals",
            "Download Festival",
            "Roskilde Festival",
            "Montreux Jazz Festival",
            "Newport Jazz Festival",
            "Berlin International Film Festival (Berlinale)",
            "Cannes Film Festival",
            "Toronto International Film Festival (TIFF)",
            "Sundance Film Festival",
            "Tribeca Film Festival",
            "Venice Film Festival",
            "The Oscars",
            "The British Open (Golf)",
            "The Masters Tournament",
            "Wimbledon",
            "US Open (Tennis)",
            "French Open (Roland Garros)",
            "Australian Open (Tennis)",
            "UEFA Champions League Final",
            "Super Bowl",
            "NBA Finals",
            "World Series",
            "Stanley Cup Finals",
            "The Masters Tournament",
            "Indy 500 (Indianapolis 500)",
            "24 Hours of Le Mans",
            "Monaco Grand Prix",
            "Kentucky Derby",
            "Melbourne Cup",
            "Royal Ascot",
            "Tour de France",
            "Giro d'Italia",
            "Vuelta a España",
            "Paris-Roubaix",
            "Boston Marathon",
            "New York City Marathon",
            "Berlin Marathon",
            "London Marathon",
            "Chicago Marathon",
            "Paris Marathon",
            "Tokyo Marathon",
            "Comrades Marathon (South Africa)",
            "Ironman World Championship (Triathlon)",
            "Rugby World Cup",
            "Cricket World Cup",
            "ICC Champions Trophy",
            "The Ashes (Cricket)",
            "Six Nations Championship (Rugby)",
            "Super Bowl",
            "FIFA World Cup",
            "UEFA European Championship (Euro)",
            "Olympic Games",
            "Commonwealth Games",
            "Asian Games",
            "Pan American Games",
            "Winter X Games",
            "Summer X Games",
            "World Chess Championship",
            "World Series of Poker (WSOP)",
            "Eurovision Song Contest",
            "Miss Universe Pageant",
            "Academy Awards",
            "Golden Globe Awards",
            "Grammy Awards",
            "Emmy Awards",
            "Tony Awards",
            "MTV Video Music Awards",
            "Coachella Valley Music and Arts Festival",
            "Glastonbury Festival",
            "Lollapalooza",
            "Bonnaroo Music and Arts Festival",
            "Reading and Leeds Festivals",
            "Tomorrowland",
            "Electric Daisy Carnival (EDC)",
            "Primavera Sound",
            "Roskilde Festival",
            "Montreux Jazz Festival",
            "Newport Jazz Festival",
            "Coachella Valley Music and Arts Festival",
            "Tomorrowland",
            "Primavera Sound",
            "Lollapalooza",
            "Electric Daisy Carnival (EDC)",
            "Glastonbury Festival",
            "Reading and Leeds Festivals",
            "Download Festival",
            "Roskilde Festival",
            "Montreux Jazz Festival",
            "Newport Jazz Festival",
            "San Diego Comic-Con International",
            "New York Comic Con",
            "Dragon Con (Atlanta)",
            "WonderCon (Anaheim)",
            "PAX (Penny Arcade Expo)",
            "Gamescom (Cologne)",
            "E3 (Electronic Entertainment Expo)",
            "Tokyo Game Show",
            "BlizzCon",
            "Anime Expo (Los Angeles)",
            "Comic-Con International: San Diego",
            "New York Comic Con",
            "Dragon Con",
            "WonderCon",
            "PAX (Penny Arcade Expo)",
            "Gamescom",
            "E3 (Electronic Entertainment Expo)",
            "Tokyo Game Show",
            "BlizzCon",
            "Anime Expo",
            "Sundance Film Festival",
            "Tribeca Film Festival",
            "Venice Film Festival",
            "Cannes Film Festival",
            "Berlin International Film Festival",
            "Toronto International Film Festival",
            "South by Southwest (SXSW)",
            "London Film Festival",
            "Edinburgh International Film Festival",
            "Tokyo International Film Festival",
            "International Documentary Film Festival Amsterdam (IDFA)",
            "Rotterdam International Film Festival",
            "Hong Kong International Film Festival",
            "Melbourne International Film Festival",
            "Sydney Film Festival",
            "Cannes Lions International Festival of Creativity",
            "Advertising Week (New York, London, Tokyo)",
            "SXSW (South by Southwest)",
            "CES (Consumer Electronics Show)",
            "Mobile World Congress (MWC)",
            "E3 (Electronic Entertainment Expo)",
            "Gamescom (Cologne)",
            "Comic-Con International: San Diego",
            "New York Comic Con",
            "PAX (Penny Arcade Expo)",
            "Tokyo Game Show",
            "BlizzCon"
            );
            eventsLength = events.size();
        }
        public String getEventName()
        {
            return this.events.get(random.nextInt(0, eventsLength));
        }
}
