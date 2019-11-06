package com.yunpian.stargate.core.exception;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/10/2 Time:下午3:24
 */
public class StargateNotReturnTypeException extends RuntimeException {

    public StargateNotReturnTypeException() {
    }

    public StargateNotReturnTypeException(String message) {
        super(message);
    }

    public StargateNotReturnTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public StargateNotReturnTypeException(Throwable cause) {
        super(cause);
    }

    public StargateNotReturnTypeException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
