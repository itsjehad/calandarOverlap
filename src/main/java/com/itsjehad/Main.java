package com.itsjehad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {

    public static void main(String[] args) {
	// write your code here
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event(now, now.plusHours(1));
        Event event2 = new Event(now.plusMinutes(20), now.plusHours(1));
        Event event5 = new Event(now.plusMinutes(10), now.plusHours(1));
        Event event6 = new Event(now.plusMinutes(-20), now.plusMinutes(70));
        Event event7 = new Event(now.plusMinutes(45), now.plusMinutes(70));
        Event event3 = new Event(now.plusHours(1).plusMinutes(9), now.plusHours(2));
        Event event4 = new Event(now.plusHours(2).plusMinutes(59), now.plusHours(4));
        Event [] event = {event1, event2, event3, event4, event5, event6, event7};
        EventOverlap eventOverlap = new EventOverlap();
        Event[] overlappingEvents = eventOverlap.findOverlaps(event);
        for (Event overlappingEvent : overlappingEvents) {
            System.out.println("Overlapped event start time: " +overlappingEvent.start().toString() +" end time:" +overlappingEvent.end().toString());
        }

    }
}
