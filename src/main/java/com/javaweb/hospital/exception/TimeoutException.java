package com.javaweb.hospital.exception;

import java.time.LocalDateTime;
import java.util.List;

public final class TimeoutException extends ApplicationException {

  private TimeoutException(String topic, LocalDateTime timeStamp) {
    super(List.of(), String.format("%s has been timed out", topic), timeStamp);
  }

  public static TimeoutException of(String topic) {
    return new TimeoutException(topic, LocalDateTime.now());
  }
}
