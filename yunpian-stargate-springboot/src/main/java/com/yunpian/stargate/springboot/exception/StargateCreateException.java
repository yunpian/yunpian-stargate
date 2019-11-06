package com.yunpian.stargate.springboot.exception;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/10/2 Time:下午3:24
 */
public class StargateCreateException extends RuntimeException {

    public StargateCreateException() {
    }

    public StargateCreateException(String message) {
        super(message);
    }

    public StargateCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public StargateCreateException(Throwable cause) {
        super(cause);
    }

    public StargateCreateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
