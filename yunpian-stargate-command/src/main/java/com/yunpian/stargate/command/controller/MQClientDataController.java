package com.yunpian.stargate.command.controller;

import com.yunpian.stargate.command.controller.form.MQClientDataForm;
import com.yunpian.stargate.command.controller.vo.MQClientDataVO;
import com.yunpian.stargate.command.dto.MQClientData;
import com.yunpian.stargate.command.service.IMQClientDataService;
import com.yunpian.stargate.command.utils.Click;
import com.yunpian.stargate.core.utils.StringUtils;
import java.text.SimpleDateFormat;
import java.util.List;
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
      if (!StringUtils.isBlank(mqClientDataForm.getTopic())) {
        if (mqClientDataForm.getFuzzy()) {
          return mqClientData.getTopic().contains(mqClientDataForm.getTopic());
        }
        return mqClientData.getTopic().equals(mqClientDataForm.getTopic());
      }
      return true;
    }).filter(mqClientData -> {
      if (!StringUtils.isBlank(mqClientDataForm.getGroup())) {
        if (mqClientDataForm.getFuzzy()) {
          return mqClientData.getGroup().contains(mqClientDataForm.getGroup());
        }
        return mqClientData.getGroup().equals(mqClientDataForm.getGroup());
      }
      return true;
    }).filter(mqClientData -> {
      if (!StringUtils.isBlank(mqClientDataForm.getAppName())) {
        if (mqClientDataForm.getFuzzy()) {
          return mqClientData.getAppName().contains(mqClientDataForm.getAppName());
        }
        return mqClientData.getAppName().equals(mqClientDataForm.getAppName());
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
}
