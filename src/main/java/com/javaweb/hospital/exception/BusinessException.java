package com.javaweb.hospital.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public final class BusinessException extends ApplicationException {

  private BusinessException(String message, List<String> fields) {
    super(
      fields,
      String.format("Business error: %s", message),
      LocalDateTime.now()
    );
  }

  public static BusinessException of(String message, String ...fields) {
    return new BusinessException(message, Arrays.stream(fields).toList());
  }

}
