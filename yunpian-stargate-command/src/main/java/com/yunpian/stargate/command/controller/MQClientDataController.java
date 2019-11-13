package com.yunpian.stargate.command.controller;

import com.yunpian.stargate.command.controller.form.MQClientDataForm;
import com.yunpian.stargate.command.controller.vo.MQClientDataVO;
import com.yunpian.stargate.command.dto.MQClientData;
import com.yunpian.stargate.command.service.IMQClientDataService;
import com.yunpian.stargate.command.utils.Click;
import com.yunpian.stargate.core.utils.StringUtils;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:6:21 PM
 */
@RestController
@RequestMapping("/mqClientData")
public class MQClientDataController {

  @Autowired
  private IMQClientDataService dataService;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @GetMapping("/list")
  public Click<List<MQClientDataVO>> list(MQClientDataForm mqClientDataForm) {
    List<MQClientData> list = dataService.list();
    List<MQClientDataVO> collect = list.stream().filter(mqClientData -> {
      if (!mqClientDataForm.getTopic().isEmpty()) {
        return mqClientDataForm.getTopic().contains(mqClientData.getTopic());
      }
      return true;
    }).filter(mqClientData -> {
      if (!mqClientDataForm.getGroup().isEmpty()) {
        return mqClientDataForm.getGroup().contains(mqClientData.getGroup());
      }
      return true;
    }).filter(mqClientData -> {
      if (!mqClientDataForm.getAppName().isEmpty()) {
        return mqClientDataForm.getAppName().contains(mqClientData.getAppName());
      }
      return true;
    }).filter(mqClientData -> {
      if (!StringUtils.isBlank(mqClientDataForm.getType())) {
        return mqClientDataForm.getType().equals(mqClientData.getType() + "");
      }
      return true;
    }).map(mqClientData -> {
      MQClientDataVO mqClientDataVO = new MQClientDataVO();
      BeanUtils.copyProperties(mqClientData, mqClientDataVO);
      if (mqClientData.getConsumerClientInfoDTO() != null) {
        BeanUtils.copyProperties(mqClientData.getConsumerClientInfoDTO(), mqClientDataVO);
      } else if (mqClientData.getProducerClientInfoDTO() != null) {
        BeanUtils.copyProperties(mqClientData.getProducerClientInfoDTO(), mqClientDataVO);
      }
      mqClientDataVO.setRefreshTime(dateFormat.format(mqClientData.getRefreshTime()));
      return mqClientDataVO;
    }).collect(Collectors.toList());
    return Click.buildSucc(collect);
  }

  @GetMapping("/groups")
  public Click<Set<String>> groups() {
    Set<String> groups = new HashSet<>();
    for (MQClientData clientData : dataService.list()) {
      groups.add(clientData.getGroup());
    }
    return Click.buildSucc(groups);
  }

  @GetMapping("/topics")
  public Click<Set<String>> topics() {
    Set<String> topics = new HashSet<>();
    for (MQClientData clientData : dataService.list()) {
      topics.add(clientData.getTopic());
    }
    return Click.buildSucc(topics);
  }

  @GetMapping("/appNames")
  public Click<Set<String>> appNames() {
    Set<String> appNames = new HashSet<>();
    for (MQClientData clientData : dataService.list()) {
      appNames.add(clientData.getAppName());
    }
    return Click.buildSucc(appNames);
  }
}
