package com.yunpian.stargate.core.process;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.vo.StargateMessage;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/8/15 Time:下午9:35
 */
public interface IStargateClientEncod {

  byte[] encod(StargateMessage stargateMessage, ProducerContext producerContext);
}
