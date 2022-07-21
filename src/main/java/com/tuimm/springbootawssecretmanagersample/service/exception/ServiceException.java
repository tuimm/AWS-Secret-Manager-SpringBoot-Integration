package com.tuimm.springbootawssecretmanagersample.service.exception;

public class ServiceException extends Exception{

  private String eErrorCode;

  public ServiceException(EErrorCode eErrorCode) {
  }
}
