package com.javaweb.hospital.controllers.exception;


import com.javaweb.hospital.exception.handler.IAppExceptionHandler;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice(basePackages = {
  "com.javaweb.hospital.controllers"
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
