package com.yunpian.stargate.command.controller;

import com.yunpian.stargate.command.service.ICommandSendService;
import com.yunpian.stargate.command.utils.Click;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:6:21 PM
 */
@RestController
@RequestMapping("/consumeManage")
public class StargateManageController {

  @Autowired
  private ICommandSendService commandSendService;

  @PostMapping("/start/{id}")
  public Click<Boolean> startConsume(@PathVariable("id") String id) {
    boolean b = commandSendService.startConsume(id);
    return Click.buildSucc(b);
  }

  @PostMapping("/stop/{id}")
  public Click<Boolean> stopConsume(@PathVariable("id") String id) {
    boolean b = commandSendService.stopConsume(id);
    return Click.buildSucc(b);
  }

  @PostMapping("/suspend/{id}")
  public Click<Boolean> suspendConsume(@PathVariable("id") String id) {
    boolean b = commandSendService.suspendConsume(id);
    return Click.buildSucc(b);
  }

  @PostMapping("/resume/{id}")
  public Click<Boolean> resumeConsume(@PathVariable("id") String id) {
    boolean b = commandSendService.resumeConsume(id);
    return Click.buildSucc(b);
  }

  @PostMapping("/start/list")
  public Click<Boolean> startConsumeList(@RequestBody List<String> ids) {
    if (ids == null || ids.size() == 0) {
      return Click.buildFaild(1, "没有可以操作的消费者");
    }
    for (String id : ids) {
      commandSendService.startConsume(id);
    }
    return Click.buildSucc();
  }

  @PostMapping("/stop/list")
  public Click<Boolean> stopConsumeList(@RequestBody List<String> ids) {
    if (ids == null || ids.size() == 0) {
      return Click.buildFaild(1, "没有可以操作的消费者");
    }
    for (String id : ids) {
      commandSendService.stopConsume(id);
    }
    return Click.buildSucc();
  }

  @PostMapping("/suspend/list")
  public Click<Boolean> suspendConsumeList(@RequestBody List<String> ids) {
    if (ids == null || ids.size() == 0) {
      return Click.buildFaild(1, "没有可以操作的消费者");
    }
    for (String id : ids) {
      commandSendService.suspendConsume(id);
    }
    return Click.buildSucc();
  }

  @PostMapping("/resume/list")
  public Click<Boolean> resumeConsumeList(@RequestBody List<String> ids) {
    if (ids == null || ids.size() == 0) {
      return Click.buildFaild(1, "没有可以操作的消费者");
    }
    for (String id : ids) {
      commandSendService.resumeConsume(id);
    }
    return Click.buildSucc();
  }
}
