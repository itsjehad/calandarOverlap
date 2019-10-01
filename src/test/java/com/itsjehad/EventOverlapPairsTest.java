package com.itsjehad;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class EventOverlapPairsTest {
    @Test
    public void testSingleEvent(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event(now, now.plusHours(1));
        Event [] event = {event1};
        EventOverlap eventOverlap = new EventOverlap();
        EventPair [] eventPairs = eventOverlap.findOverlappingPairs(event);
        EventPair[] expectedEventPairs = {};
        assertArrayEquals(expectedEventPairs, eventPairs);

    }

    @Test
    public void testEmptyOverlaps(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event [] event = {};
        EventOverlap eventOverlap = new EventOverlap();
        EventPair [] eventPairs = eventOverlap.findOverlappingPairs(event);
        EventPair[] expectedEventPairs = {};
        assertArrayEquals(expectedEventPairs, eventPairs);
    }

    @Test
    public void testNoOverlaps(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event(now, now.plusHours(1));
        Event event2 = new Event(now.plusMinutes(61), now.plusHours(2));
        Event [] event = {event1, event2};
        EventOverlap eventOverlap = new EventOverlap();
        EventPair [] eventPairs = eventOverlap.findOverlappingPairs(event);
        EventPair[] expectedEventPairs = {};
        assertArrayEquals(expectedEventPairs, eventPairs);

    }

    @Test
    public void testSingleOverlaps(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event(now, now.plusHours(1));
        Event event2 = new Event(now.plusMinutes(59), now.plusHours(2));
        Event event3 = new Event(now.plusMinutes(121), now.plusHours(3));
        Event [] event = {event1, event2, event3};
        EventOverlap eventOverlap = new EventOverlap();
        EventPair [] eventPairs = eventOverlap.findOverlappingPairs(event);
        EventPair [] expectedEventPairs = {new EventPair(event1, event2)};
        assertEquals(expectedEventPairs.length, eventPairs.length);
        for(int i = 0; i< eventPairs.length; i++) {
            assertEquals(expectedEventPairs[i].getEvent1(), eventPairs[i].getEvent1());
            assertEquals(expectedEventPairs[i].getEvent2(), eventPairs[i].getEvent2());
        }

    }

    @Test
    public void testUnsortedOverlaps(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event(now, now.plusHours(1));
        Event event2 = new Event(now.plusMinutes(59), now.plusHours(2));
        Event [] event = {event2, event1};
        EventOverlap eventOverlap = new EventOverlap();
        EventPair [] eventPairs = eventOverlap.findOverlappingPairs(event);
        EventPair [] expectedEventPairs = {new EventPair(event1, event2)};
        assertEquals(expectedEventPairs.length, eventPairs.length);
        for(int i = 0; i< eventPairs.length; i++) {
            assertEquals(expectedEventPairs[i].getEvent1(), eventPairs[i].getEvent1());
            assertEquals(expectedEventPairs[i].getEvent2(), eventPairs[i].getEvent2());
        }

    }

    @Test
    public void testSkippedOverlaps(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event(now, now.plusHours(1));
        Event event2 = new Event(now.plusMinutes(20), now.plusHours(1));
        Event event5 = new Event(now.plusMinutes(10), now.plusHours(1));
        Event event3 = new Event(now.plusHours(1).plusMinutes(9), now.plusHours(2));
        Event event4 = new Event(now.plusHours(1).plusMinutes(59), now.plusHours(4));

        Event [] event = {event1, event2, event5, event3, event4};
        EventOverlap eventOverlap = new EventOverlap();


        EventPair [] eventPairs = eventOverlap.findOverlappingPairs(event);
        EventPair [] expectedEventPairs = {new EventPair(event1, event5), new EventPair(event1, event2), new EventPair(event5, event2), new EventPair(event3, event4)};
        assertEquals(expectedEventPairs.length, eventPairs.length);
        for(int i = 0; i< eventPairs.length; i++) {
            assertEquals(expectedEventPairs[i].getEvent1(), eventPairs[i].getEvent1());
            assertEquals(expectedEventPairs[i].getEvent2(), eventPairs[i].getEvent2());
        }


    }

    @Test
    public void testMultipleOverlaps(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event(now, now.plusHours(1));
        Event event2 = new Event(now.plusMinutes(20), now.plusHours(1));
        Event event5 = new Event(now.plusMinutes(10), now.plusHours(1));
        Event event6 = new Event(now.plusMinutes(-20), now.plusMinutes(70));
        Event event7 = new Event(now.plusMinutes(45), now.plusMinutes(70));

        Event [] event = {event1, event2, event5, event6, event7};
        EventOverlap eventOverlap = new EventOverlap();
        EventPair [] eventPairs = eventOverlap.findOverlappingPairs(event);
        EventPair [] expectedEventPairs = {new EventPair(event6, event1),
                                            new EventPair(event6, event5),
                                            new EventPair(event6, event2),
                                            new EventPair(event6, event7),
                                            new EventPair(event1, event5),
                                            new EventPair(event1, event2),
                                            new EventPair(event1, event7),
                new EventPair(event5, event2),
                new EventPair(event5, event7),
                new EventPair(event2, event7),
                };
        assertEquals(expectedEventPairs.length, eventPairs.length);
        for(int i = 0; i< eventPairs.length; i++) {
            assertEquals(expectedEventPairs[i].getEvent1(), eventPairs[i].getEvent1());
            assertEquals(expectedEventPairs[i].getEvent2(), eventPairs[i].getEvent2());
        }

    }

}
