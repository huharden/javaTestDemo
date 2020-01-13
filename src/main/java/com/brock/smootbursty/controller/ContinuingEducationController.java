package com.brock.smootbursty.controller;

import com.brock.smootbursty.model.XmlDataList;
import com.brock.smootbursty.model.XmlParameter;
import com.brock.smootbursty.service.ContinuingEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/education")
public class ContinuingEducationController {

    @Autowired
    private ContinuingEducationService continuingEducationService;

    @GetMapping("token")
    public void getToken(){
        XmlParameter xmlParameter = new XmlParameter();
        xmlParameter.setEdyear(2019);
        xmlParameter.setCardnum("360121199999999999");
        xmlParameter.setCardtype((byte) 1);
        xmlParameter.setName("hutao");

        List<XmlDataList> list = new ArrayList<>();
        XmlDataList xmlDataList = new XmlDataList();
        xmlDataList.setHours(24);
        xmlDataList.setKmdm("025");
        xmlDataList.setPassdate("2019-05-17");
        xmlDataList.setReason("通过考试");
        list.add(xmlDataList);

        xmlParameter.setDataList(list);


        File dest = new File("D:\\Users\\Administrator\\Desktop\\param.xml");

        continuingEducationService.getToken(xmlParameter);



    }
}
