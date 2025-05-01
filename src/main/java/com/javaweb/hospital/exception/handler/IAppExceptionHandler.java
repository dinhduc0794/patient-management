package com.javaweb.hospital.exception.handler;


import com.hrm.leavemanagement.service.exception.ApplicationException;

public interface IAppExceptionHandler {

  ApplicationException convert(Throwable ex);
}
