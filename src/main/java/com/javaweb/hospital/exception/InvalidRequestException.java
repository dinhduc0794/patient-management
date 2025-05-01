package com.javaweb.hospital.exception;


import java.time.LocalDateTime;
import java.util.List;

public final class InvalidRequestException extends ApplicationException {

  private InvalidRequestException(String message) {
    super(List.of(), message, LocalDateTime.now());
  }

  public static InvalidRequestException of(String message) {
    return new InvalidRequestException(message);
  }
}
