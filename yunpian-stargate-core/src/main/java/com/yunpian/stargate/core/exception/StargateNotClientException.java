package com.yunpian.stargate.core.exception;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/10/2 Time:下午3:24
 */
public class StargateNotClientException extends RuntimeException {

    public StargateNotClientException() {
    }

    public StargateNotClientException(String message) {
        super(message);
    }

    public StargateNotClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public StargateNotClientException(Throwable cause) {
        super(cause);
    }

    public StargateNotClientException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
