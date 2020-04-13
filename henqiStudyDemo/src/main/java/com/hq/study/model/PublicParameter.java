package com.hq.study.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 */

@Data
@Accessors(chain = true)
public class PublicParameter implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 证书秘钥
   */
  private String client_secret;

  /**
   * 证书id
   **/
  private String client_id;

  /**
   * 系统当前时间戳
   **/
  private String timestamp;

  /**
   * Xml被Base64加密后得到的报文
   **/
  private String exxml;

  /**
   * 加密签名
   **/
  private String sign;


}
