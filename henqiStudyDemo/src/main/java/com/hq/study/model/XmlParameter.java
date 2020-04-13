package com.hq.study.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 *
 */

@Data
@Accessors(chain = true)
public class XmlParameter implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 继续教育年度
   */
  private Integer edyear;

  /**
   * 证件类型
   **/
  private byte cardtype;

  /**
   * 证件号码
   **/
  private String cardnum;

  /**
   * 姓名
   **/
  private String name;


  private List<XmlDataList> dataList;






}
