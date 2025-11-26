package com.shimo.sdk.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class SdkException extends Exception {
    private int statusCode;
    private String errorMessage;

    public SdkException() {
        super();
    }

    public SdkException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public SdkException(Throwable cause) {
        super(cause);
    }

    public SdkException(String message, Throwable cause) {
        super(message, cause);
        this.errorMessage = message;
    }

    public SdkException(int statusCode, String errorMessage) {
        super(errorMessage);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

}
