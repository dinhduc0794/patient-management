package com.javaweb.hospital.exception;

import java.time.LocalDateTime;
import java.util.List;


public final class ModelNotFoundException extends ApplicationException {

  private ModelNotFoundException(String fieldName, String entityName) {
    super(
      List.of(fieldName),
      String.format("The %s is not found", entityName),
      LocalDateTime.now()
    );
  }

  public static ModelNotFoundException of(String fieldName, String entityName) {
    return new ModelNotFoundException(fieldName, entityName);
  }
}
