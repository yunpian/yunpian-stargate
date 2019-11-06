package com.yunpian.stargate.core.exception;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/10/2 Time:下午3:24
 */
public class StargateTopicAndTagException extends RuntimeException {

    public StargateTopicAndTagException() {
    }

    public StargateTopicAndTagException(String message) {
        super(message);
    }

    public StargateTopicAndTagException(String message, Throwable cause) {
        super(message, cause);
    }

    public StargateTopicAndTagException(Throwable cause) {
        super(cause);
    }

    public StargateTopicAndTagException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
