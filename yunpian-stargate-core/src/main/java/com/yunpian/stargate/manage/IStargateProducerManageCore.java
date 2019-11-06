package com.yunpian.stargate.manage;

import com.yunpian.stargate.manage.dto.StargateProducerManageDTO;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:11:27 AM
 */
public interface IStargateProducerManageCore extends IStargateProcessManage {

  List<StargateProducerManageDTO> producerList();
}
