package com.javaweb.hospital.exception;

import java.time.LocalDateTime;
import java.util.List;

public final class InvalidFieldException extends ApplicationException {


  private InvalidFieldException(String message, List<String> fields) {
    super(
      fields,
      String.format("The field %s %s", fields.getFirst(), message),
      LocalDateTime.now()
    );
  }

  public static InvalidFieldException of(String message, String ...fields) {
    return new InvalidFieldException(message, List.of(fields));
  }


}
