package com.javaweb.hospital.controllers.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaweb.hospital.exception.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record RestExceptionResponse(
  int status,
  String message,
  @JsonProperty(value = "time_stamp") LocalDateTime timeStamp,
  List<String> fields
) implements Serializable {

  public static RestExceptionResponse of(ApplicationException e) {
    return switch(e) {
      case BusinessException ex ->
        new RestExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getTimeStamp(), ex.getFields());
      case ModelNotFoundException ex ->
        new RestExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getTimeStamp(), ex.getFields());
      case DuplicateFieldException ex ->
        new RestExceptionResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), ex.getTimeStamp(), ex.getFields());
      case UnauthorizedException ex ->
        new RestExceptionResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), ex.getTimeStamp(), ex.getFields());
      case InternalServerException ex ->
        new RestExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex.getTimeStamp(), ex.getFields());
      case InvalidFieldException ex ->
        new RestExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getTimeStamp(), ex.getFields());
      case InvalidRequestException ex ->
        new RestExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getTimeStamp(), ex.getFields());
      case TimeoutException ex ->
        new RestExceptionResponse(HttpStatus.REQUEST_TIMEOUT.value(), ex.getMessage(), ex.getTimeStamp(), ex.getFields());
    };
  }
}
