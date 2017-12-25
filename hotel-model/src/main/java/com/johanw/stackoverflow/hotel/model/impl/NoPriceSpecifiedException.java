package com.johanw.stackoverflow.hotel.model.impl;

// https://stackoverflow.com/questions/15542608/design-patterns-exception-error-handling
public class NoPriceSpecifiedException extends RuntimeException {
    public NoPriceSpecifiedException() {
        super();
    }

    public NoPriceSpecifiedException(String message) {
        super(message);
    }

    public NoPriceSpecifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPriceSpecifiedException(Throwable cause) {
        super(cause);
    }

    protected NoPriceSpecifiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

