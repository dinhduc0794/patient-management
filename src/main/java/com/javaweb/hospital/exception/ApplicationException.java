package com.javaweb.hospital.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public sealed abstract class ApplicationException extends RuntimeException
  permits DuplicateFieldException, ModelNotFoundException, InternalServerException, InvalidFieldException,
    UnauthorizedException, BusinessException, InvalidRequestException, TimeoutException {

  private List<String> fields;
  private String message;
  private LocalDateTime timeStamp;

  protected ApplicationException(Throwable cause) {
    super(cause);
  }
}
