package com.yunpian.stargate.core.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/12/4 Time:6:52 PM
 */
public class StargateMessage<T> implements Serializable {

    private static final long serialVersionUID = -4736121432533555463L;

    private T body;
    private long delayMsec;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public long getDelayMsec() {
        return delayMsec;
    }

    public void setDelayMsec(long delayMsec) {
        this.delayMsec = delayMsec;
    }

    @Override
    public String toString() {
        return "StargateMessage{" +
                "body=" + body +
                ", delayMsec=" + delayMsec +
                '}';
    }

    public StargateMessage() {
    }

    public StargateMessage(T body, long delayMsec) {
        this.body = body;
        this.delayMsec = delayMsec;
    }

    public StargateMessage(T body) {
        this.body = body;
    }
}
