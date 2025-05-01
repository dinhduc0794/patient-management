package com.javaweb.hospital.exception;


import java.time.LocalDateTime;
import java.util.List;

public final class UnauthorizedException extends ApplicationException {

  private UnauthorizedException(String fieldName) {
    super(
      List.of(fieldName),
      String.format("The %s is unauthorized", fieldName),
      LocalDateTime.now()
    );
  }

  public static UnauthorizedException of(String fieldName)  {
    return new UnauthorizedException(fieldName);
  }
}
