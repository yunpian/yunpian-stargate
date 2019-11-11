package com.yunpian.stargate.command.config;

import com.yunpian.stargate.command.utils.Click;
import com.yunpian.stargate.command.utils.Click.Code;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.yunpian.stargate.command")
public class GlobalExceptionHandler {

  private static Logger logger = LoggerFactory.getLogger("exception");

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Click defaultErrorHandler(HttpServletRequest req, Exception ex) {
    logger.error("unknow exception", ex);
    return Click.buildFaild(Code.UNKNOWN);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseBody
  public Click httpMessageNotReadableException(HttpServletRequest req,
    HttpMessageNotReadableException ex) {
    logger.error("unknow exception", ex);
    return Click.buildFaild(Code.UNKNOWN, ex.getMessage());
  }

  @ExceptionHandler(HttpMessageNotWritableException.class)
  @ResponseBody
  public Click httpMessageNotWritableException(HttpServletRequest req,
    HttpMessageNotReadableException ex) {
    logger.error("unknow exception", ex);
    return Click.buildFaild(Code.UNKNOWN, ex.getMessage());
  }
}
