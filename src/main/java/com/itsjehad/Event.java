package com.itsjehad;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{
  private LocalDateTime start;
  private LocalDateTime end;

  public Event(LocalDateTime start, LocalDateTime end) {
    if (start.isAfter(end)) {
      throw new IllegalArgumentException("event ends before it starts!");
    }

    this.start = start;
    this.end = end;
  }

  public LocalDateTime start() { return start; }
  public LocalDateTime end() { return end; }

  public boolean overlaps(Event that) {
    return this.start().isBefore(that.end) && that.start().isBefore(this.end());
  }

  public boolean endsBefore(Event that) {
    return this.end.isBefore(that.end);
  }
  @Override
  public int compareTo(Event o) {
    return this.start().compareTo(o.start());
  }
}