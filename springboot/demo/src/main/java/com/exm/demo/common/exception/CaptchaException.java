package com.exm.demo.common.exception;

public class CaptchaException extends UserException{
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
