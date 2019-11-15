package com.yunpian.stargate.command.service.impl;

import com.yunpian.stargate.command.dto.MQClientData;
import com.yunpian.stargate.command.mq.producer.CommandSendProducer;
import com.yunpian.stargate.command.service.ICommandSendService;
import com.yunpian.stargate.command.service.IMQClientDataService;
import com.yunpian.stargate.dogrobber.dto.CommandHandleDTO;
import com.yunpian.stargate.dogrobber.enums.CommandHandleCodeEnum;
import com.yunpian.stargate.dogrobber.enums.CommandHandleIdCardEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:6:13 PM
 */
@Service
public class CommandSendServiceImpl implements ICommandSendService {

  @Autowired
  private CommandSendProducer commandSendProducer;
  @Autowired
  private IMQClientDataService clientDataService;

  @Override
  public void refresh() {
    CommandHandleDTO commandHandleDTO = new CommandHandleDTO();
    commandHandleDTO.setIdCard(CommandHandleIdCardEnum.ALL_COMMAND.getIdCard());
    commandHandleDTO.setCode(CommandHandleCodeEnum.REFRESH_DATA.getCode());
    clientDataService.removeAll();
    commandSendProducer.send(commandHandleDTO);
  }

  @Override
  public boolean setThreadSize(String id, Integer threadSize) {
    CommandHandleDTO commandHandleDTO = createByMQClientDataId(id);
    commandHandleDTO.setCode(CommandHandleCodeEnum.SET_THREAD_SIZE.getCode());
    commandHandleDTO.setData(threadSize);
    commandSendProducer.send(commandHandleDTO);
    return true;
  }

  @Override
  public boolean startConsume(String id) {
    CommandHandleDTO commandHandleDTO = createByMQClientDataId(id);
    commandHandleDTO.setCode(CommandHandleCodeEnum.START_CONSUMER.getCode());
    commandSendProducer.send(commandHandleDTO);
    return true;
  }

  @Override
  public boolean stopConsume(String id) {
    CommandHandleDTO commandHandleDTO = createByMQClientDataId(id);
    commandHandleDTO.setCode(CommandHandleCodeEnum.STOP_CONSUMER.getCode());
    commandSendProducer.send(commandHandleDTO);
    return true;
  }

  @Override
  public boolean suspendConsume(String id) {
    CommandHandleDTO commandHandleDTO = createByMQClientDataId(id);
    commandHandleDTO.setCode(CommandHandleCodeEnum.SUSPEND_CONSUMER.getCode());
    commandSendProducer.send(commandHandleDTO);
    return true;
  }

  @Override
  public boolean resumeConsume(String id) {
    CommandHandleDTO commandHandleDTO = createByMQClientDataId(id);
    commandHandleDTO.setCode(CommandHandleCodeEnum.RESUME_CONSUMER.getCode());
    commandSendProducer.send(commandHandleDTO);
    return true;
  }

  private CommandHandleDTO createByMQClientDataId(String id) {
    MQClientData mqClientData = clientDataService.get(id);
    CommandHandleDTO commandHandleDTO = new CommandHandleDTO();
    commandHandleDTO.setKey(mqClientData.getKey());
    commandHandleDTO.setIdCard(mqClientData.getIdCard());
    return commandHandleDTO;
  }
}
