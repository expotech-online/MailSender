package org.ahmedukamel.mailesender.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final String message;
    private final Object data;

    public CustomException(String message, Object data) {
        super(message);
        this.message = message;
        this.data = data;
    }
}
