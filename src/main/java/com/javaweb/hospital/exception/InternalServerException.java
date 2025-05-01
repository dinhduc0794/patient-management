package com.javaweb.hospital.exception;

import java.time.LocalDateTime;
import java.util.List;

public final class InternalServerException extends ApplicationException {

  private InternalServerException() {
    super(
      List.of(),
      "The internal server error occurred",
      LocalDateTime.now()
    );
  }

  private InternalServerException(String message) {
    super(
      List.of(),
      message,
      LocalDateTime.now()
    );
  }

  private InternalServerException(Throwable cause) {
    super(
      List.of(),
      cause.getMessage(),
      LocalDateTime.now()
    );
  }

  public static InternalServerException of(String message) {
    return new InternalServerException(message);
  }

  public static InternalServerException of() {
    return new InternalServerException();
  }

  public static InternalServerException of(Throwable cause) {
    return new InternalServerException(cause);
  }
}
