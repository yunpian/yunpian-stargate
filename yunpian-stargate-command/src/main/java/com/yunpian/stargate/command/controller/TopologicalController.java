package com.yunpian.stargate.command.controller;

import com.yunpian.stargate.command.controller.form.TopologicalForm;
import com.yunpian.stargate.command.controller.vo.TopologicalLinkVO;
import com.yunpian.stargate.command.controller.vo.TopologicalNodeVO;
import com.yunpian.stargate.command.controller.vo.TopologicalVO;
import com.yunpian.stargate.command.dto.MQClientData;
import com.yunpian.stargate.command.service.IMQClientDataService;
import com.yunpian.stargate.command.utils.Click;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/11 Time:12:27 PM
 */
@RestController
@RequestMapping("/topological")
public class TopologicalController {

  @Autowired
  private IMQClientDataService dataService;

  @GetMapping("/list")
  public Click<TopologicalVO> list(TopologicalForm topologicalForm) {
    TopologicalVO topologicalVO = new TopologicalVO();
    List<MQClientData> list = dataService.list();
    List<MQClientData> collect = list.stream().filter(mqClientData -> {
      if (!topologicalForm.getTopic().isEmpty()) {
        return topologicalForm.getTopic().contains(mqClientData.getTopic());
      }
      return true;
    }).filter(mqClientData -> {
      if (!topologicalForm.getGroup().isEmpty()) {
        return topologicalForm.getGroup().contains(mqClientData.getGroup());
      }
      return true;
    }).filter(mqClientData -> {
      if (!topologicalForm.getAppName().isEmpty()) {
        return topologicalForm.getAppName().contains(mqClientData.getAppName());
      }
      return true;
    }).map(mqClientData -> {
      TopologicalLinkVO topologicalLinkVO = new TopologicalLinkVO();
      TopologicalNodeVO topologicalNodeVO = new TopologicalNodeVO();
      TopologicalNodeVO topologicalTopicNodeVO = new TopologicalNodeVO();
      if (mqClientData.getType() == 0) {
        topologicalLinkVO.setSource(mqClientData.getTopic());
        topologicalLinkVO.setTarget(mqClientData.getAppName());
      } else if (mqClientData.getType() == 1) {
        topologicalLinkVO.setSource(mqClientData.getAppName());
        topologicalLinkVO.setTarget(mqClientData.getTopic());
      }
      topologicalNodeVO.setName(mqClientData.getAppName());
      topologicalNodeVO.setSymbolSize(4);
      topologicalNodeVO.setValue(1);
      topologicalNodeVO.setCategory(0);
      topologicalTopicNodeVO.setName(mqClientData.getTopic());
      topologicalTopicNodeVO.setSymbolSize(1);
      topologicalTopicNodeVO.setValue(1);
      topologicalTopicNodeVO.setCategory(1);
      topologicalVO.addLink(topologicalLinkVO);
      topologicalVO.addNode(topologicalNodeVO);
      topologicalVO.addNode(topologicalTopicNodeVO);
      return mqClientData;
    }).collect(Collectors.toList());
    return Click.buildSucc(topologicalVO);
  }
}
