package com.hq.study.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 */

@Data
@Accessors(chain = true)
public class Book implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  /**
   * 用户名 username
   **/
  private String name;

  /**
   * 用户操作 operation
   **/
  private String author;

  /**
   * 请求方法 method
   **/
  private float price;


}
