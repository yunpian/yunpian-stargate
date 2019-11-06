package com.yunpian.stargate.core.exception;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/10/2 Time:下午3:24
 */
public class StargateEncodException extends RuntimeException {

    public StargateEncodException() {
    }

    public StargateEncodException(String message) {
        super(message);
    }

    public StargateEncodException(String message, Throwable cause) {
        super(message, cause);
    }

    public StargateEncodException(Throwable cause) {
        super(cause);
    }

    public StargateEncodException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
