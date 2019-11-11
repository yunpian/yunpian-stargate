package com.yunpian.stargate.command.service;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:6:13 PM
 */
public interface ICommandSendService {

  boolean startConsume(String id);

  boolean stopConsume(String id);

  boolean suspendConsume(String id);

  boolean resumeConsume(String id);
}
