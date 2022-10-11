package com.ravi.library.bookmgmt.exception;

public class BookMgmtCommonException extends  RuntimeException{

    public BookMgmtCommonException() {
    }

    public BookMgmtCommonException(String message) {
        super(message);
    }

    public BookMgmtCommonException(Exception message) {
        super(message.getMessage());
    }
}

