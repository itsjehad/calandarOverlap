package com.itsjehad;

class EventPair {
    private Event event1;
    private Event event2;

    EventPair(Event event1, Event event2) {
        this.event1 = event1;
        this.event2 = event2;
    }

    Event getEvent1() {
        return event1;
    }

    Event getEvent2() {
        return event2;
    }
}
