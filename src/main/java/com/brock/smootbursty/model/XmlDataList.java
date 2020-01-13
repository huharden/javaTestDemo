package com.brock.smootbursty.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */

@Data
@Accessors(chain = true)
public class XmlDataList implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 继续教育科目
   **/
  private String kmdm;

  /**
   * 学分
   **/
  private float hours;

  /**
   * 继续教育培训通过时间
   **/
  private String passdate;

   /**
   * 课程内容通过原因
   **/
  private String reason;


}
