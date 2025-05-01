package com.javaweb.hospital.exception;



import java.time.LocalDateTime;
import java.util.List;

public final class DuplicateFieldException extends ApplicationException {

  private DuplicateFieldException(String fieldName) {
    super(
      List.of(fieldName),
      String.format("The field %s has already existed", fieldName),
      LocalDateTime.now()
    );
  }

  private DuplicateFieldException(String entityName, String ...fieldNames) {
    super(
      List.of(fieldNames),
      String.format("The %s has already existed", entityName),
      LocalDateTime.now()
    );
  }

  public static DuplicateFieldException of(String fieldName) {
    return new DuplicateFieldException(fieldName);
  }

  public static DuplicateFieldException of(String entityName, String ...fieldNames) {
    return new DuplicateFieldException(entityName, fieldNames);
  }
}
