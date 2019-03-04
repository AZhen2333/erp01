package com.dg.exception;

public class ServiceException extends RuntimeException {
    private Integer code;
    private String errorMessage;

    public ServiceException(Integer code, String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(BizExceptionEnum exception) {
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }
}
