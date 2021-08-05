package com.exm.demo.common.exception;

public class UserNotExistsException extends UserException{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
