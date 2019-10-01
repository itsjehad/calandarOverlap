package com.itsjehad;

import java.util.Arrays;
import java.util.TreeSet;


public class EventOverlap {
  public Event[] findOverlaps(Event[] input) {
    if (input == null || input.length <= 1) {
      return new Event[] {};
    }

    TreeSet<Event> overlapping = new TreeSet<Event>();
    Event[] events = copy(input);
    Arrays.sort(events);

    findOverlaps(events, overlapping);
    return overlapping.toArray(new Event[] {});
  }

  private void findOverlaps(Event[] sortedEvents, TreeSet<Event> overlapping) {
    Event prvEvent = sortedEvents[0];
    if(sortedEvents.length > 0) {
      var prvIndex = 0;
      for (int index = 1; index < sortedEvents.length; index++) {
        Event currentEvent = sortedEvents[index];
        if (prvEvent.overlaps(sortedEvents[index])) {
          //move to the next event if it's endtime is after the previous event
          overlapping.add(prvEvent);
          overlapping.add(currentEvent);
          if (prvEvent.endsBefore(sortedEvents[index])) {
            prvIndex = index;
            prvEvent = sortedEvents[index];
          }
        } else {
          //No overlapping... move to the next event
          prvIndex = index;
          prvEvent = sortedEvents[index];
        }
      }
    }
  }

  private Event[] copy(Event[] events) {
    Event[] copied = new Event[ events.length ];
    
    for (int i = 0; i < events.length; i++) {
      copied[i] = events[i];
    }

    return copied;
  }
}