package com.yunpian.stargate.dogrobber.consumer;

import com.yunpian.stargate.annotation.StargateBody;
import com.yunpian.stargate.annotation.StargateBroadcasting;
import com.yunpian.stargate.annotation.StargateConsumer;
import com.yunpian.stargate.annotation.StargateDecode;
import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.dogrobber.dto.CommandHandleDTO;
import com.yunpian.stargate.dogrobber.enums.CommandHandleIdCardEnum;
import com.yunpian.stargate.dogrobber.timejob.UploadStatusJob;
import com.yunpian.stargate.jackson.JacksonStargateClientDecode;
import com.yunpian.stargate.manage.StargateCoreManageInstance;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:4:53 PM
 */
@StargateConsumer
public class CommandHandleConsumer {

  @StargateMapper(CommandHandleDTO.TOPIC)
  @StargateBroadcasting
  @StargateDecode(JacksonStargateClientDecode.class)
  public void handle(@StargateBody CommandHandleDTO commandHandleDTO) {
    if (!StargateCoreManageInstance.getIdCard().equals(commandHandleDTO.getIdCard()) &&
      !CommandHandleIdCardEnum.ALL_COMMAND.getIdCard().equals(commandHandleDTO.getIdCard())) {
      return;
    }
    switch (commandHandleDTO.getCode()) {
      case 101:
        //11是启动
        StargateCoreManageInstance.getStargateConsumeCoreManage()
          .startConsumeByKey(commandHandleDTO.getKey());
        UploadStatusJob.sendOnce();
        break;
      case 102:
        //12是停止
        StargateCoreManageInstance.getStargateConsumeCoreManage()
          .stopConsumeByKey(commandHandleDTO.getKey());
        UploadStatusJob.sendOnce();
        break;
      case 103:
        //13是暂停
        StargateCoreManageInstance.getStargateConsumeCoreManage()
          .suspendConsumeByKey(commandHandleDTO.getKey());
        UploadStatusJob.sendOnce();
        break;
      case 104:
        //14是恢复
        StargateCoreManageInstance.getStargateConsumeCoreManage()
          .resumeConsumeByKey(commandHandleDTO.getKey());
        UploadStatusJob.sendOnce();
        break;
      case 105:
        //15是刷新
        UploadStatusJob.sendOnce();
        break;
      case 106:
        //16是修改线程池大小
        StargateCoreManageInstance.getStargateConsumeCoreManage()
          .updateThreadSize(commandHandleDTO.getKey(), (Integer) commandHandleDTO.getData());
        UploadStatusJob.sendOnce();
        break;
      //2开始是生产者
      case 201:
        break;
      default:
        break;
    }
  }
}
