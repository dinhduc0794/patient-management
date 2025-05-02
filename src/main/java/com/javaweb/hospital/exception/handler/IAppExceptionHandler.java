package com.javaweb.hospital.exception.handler;


import com.javaweb.hospital.exception.ApplicationException;

public interface IAppExceptionHandler {

  ApplicationException convert(Throwable ex);
}
