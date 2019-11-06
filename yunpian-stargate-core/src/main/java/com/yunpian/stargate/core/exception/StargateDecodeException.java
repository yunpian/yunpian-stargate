package com.yunpian.stargate.core.exception;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/10/2 Time:下午3:24
 */
public class StargateDecodeException extends RuntimeException {

    public StargateDecodeException() {
    }

    public StargateDecodeException(String message) {
        super(message);
    }

    public StargateDecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public StargateDecodeException(Throwable cause) {
        super(cause);
    }

    public StargateDecodeException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
