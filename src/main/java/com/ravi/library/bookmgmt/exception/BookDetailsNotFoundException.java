package com.ravi.library.bookmgmt.exception;

public class BookDetailsNotFoundException extends  BookMgmtCommonException{

    public BookDetailsNotFoundException() {
        super("Book Details are not found...");
    }

    public BookDetailsNotFoundException(String message) {
        super(message);
    }

    public BookDetailsNotFoundException(Throwable cause) {
        super(cause.getMessage());
    }
}
