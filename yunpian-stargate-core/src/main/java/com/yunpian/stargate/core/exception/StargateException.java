package com.yunpian.stargate.core.exception;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/10/2 Time:下午3:24
 */
public class StargateException extends RuntimeException {

    public StargateException() {
    }

    public StargateException(String message) {
        super(message);
    }

    public StargateException(String message, Throwable cause) {
        super(message, cause);
    }

    public StargateException(Throwable cause) {
        super(cause);
    }

    public StargateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
