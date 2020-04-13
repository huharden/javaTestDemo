package com.hq.study.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 */

@Data
@Accessors(chain = true)
public class TokenParameter implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 调用接口类型1001
   */
  private String excode;

  /**
   * 证书id
   **/
  private String client_id;

  /**
   * 系统当前时间戳
   **/
  private String timestamp;

  /**
   * 填入fin固定
   **/
  private String exxml;

  /**
   * 填入001固定
   **/
  private String sign;


}
