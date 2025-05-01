package com.javaweb.hospital.controllers.exception;


import com.javaweb.hospital.exception.handler.IAppExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {
  "con,javaweb.hospital"
})
public class RestExceptionHandler {

  private final IAppExceptionHandler handler;

  @Autowired
  public RestExceptionHandler(IAppExceptionHandler handler) {
    this.handler = handler;
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<RestExceptionResponse> handleAppException(RuntimeException e) {
    RestExceptionResponse response = RestExceptionResponse.of(this.handler.convert(e));
    return ResponseEntity.status(response.status()).body(response);
  }


}
