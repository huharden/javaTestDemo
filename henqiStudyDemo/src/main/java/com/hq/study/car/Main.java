package com.hq.study.car;

import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-08-16-11:03
 */
public class Main {
    public static void main(String[] args){


        List<AIMsgVO> aiMsgVOList = new ArrayList<>();
        AIMsgVO aiMsgVO = new AIMsgVO();
        aiMsgVO.setContent("djjjjjjk");
        aiMsgVO.setStudent_pic("hhdhdhh");
        aiMsgVOList.add(aiMsgVO);
        System.out.println(aiMsgVOList.toString());

        JSONObject aa = JSONObject.fromObject(aiMsgVO);
        System.out.println(aa.toString());

        System.out.println(aa);






    }
}
