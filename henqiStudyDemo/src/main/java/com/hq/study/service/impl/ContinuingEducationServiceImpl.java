package com.hq.study.service.impl;

import com.hq.study.model.XmlDataList;
import com.hq.study.model.XmlParameter;
import com.hq.study.service.ContinuingEducationService;
import com.hq.study.utils.XmlMapper;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

@Service
public class ContinuingEducationServiceImpl implements ContinuingEducationService {


    @Override
    public String getToken(XmlParameter xmlParameter){

        StringBuilder stringBuilder = new StringBuilder();

        String head = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        String xml =  XmlMapper.toXml(xmlParameter);
        stringBuilder.append(head);
        stringBuilder.append(xml);

       return stringBuilder.toString();
    }

    public String getToken(XmlParameter xmlParameter, File dest){

        // 创建SAXTransformerFactory对象
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        try {
            // 通过SAXTransformerFactory对象创建TransformerHandler对象
            TransformerHandler handler = factory.newTransformerHandler();
            // 通过Handler创建Transformer对象
            Transformer transformer = handler.getTransformer();
            // 设置Transformer的属性
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // 创建Result对象，并将目的XML文件与其关联
            Result result = new StreamResult(dest);
            // 将handler与result关联起来
            handler.setResult(result);

            try {
                // 开启文档
                handler.startDocument();

                // 新建节点
                handler.startElement("", "", "fin", null);
                handler.startElement("", "", "msg", null);
                handler.startElement("", "", "param", null);
                //继续教育年度
                handler.startElement("", "", "edyear", null);
                handler.characters(String.valueOf(xmlParameter.getEdyear()).toCharArray(), 0, String.valueOf(xmlParameter.getEdyear()).length());
                handler.endElement("", "", "edyear");
                //证件类型
                handler.startElement("", "", "cardtype", null);
                handler.characters(String.valueOf(xmlParameter.getCardtype()).toCharArray(), 0, String.valueOf(xmlParameter.getCardtype()).length());
                handler.endElement("", "", "cardtype");
                //证件号码
                handler.startElement("", "", "cardnum", null);
                handler.characters(xmlParameter.getCardnum().toCharArray(), 0, xmlParameter.getCardnum().length());
                handler.endElement("", "", "cardnum");
                //证件号码
                handler.startElement("", "", "name", null);
                handler.characters(xmlParameter.getName().toCharArray(), 0, xmlParameter.getName().length());
                handler.endElement("", "", "name");

                for (XmlDataList dataList : xmlParameter.getDataList()) {
                    //继续教育科目
                    handler.startElement("", "", "kmdm", null);
                    handler.characters(dataList.getKmdm().toCharArray(), 0, dataList.getKmdm().length());
                    handler.endElement("", "", "kmdm");
                    //学分
                    handler.startElement("", "", "hours", null);
                    handler.characters(Float.toString(dataList.getHours()).toCharArray(), 0, Float.toString(dataList.getHours()).length());
                    handler.endElement("", "", "hours");
                    //继续教育培训通过时间
                    handler.startElement("", "", "passdate", null);
                    handler.characters(dataList.getPassdate().toCharArray(), 0, dataList.getPassdate().length());
                    handler.endElement("", "", "passdate");
                    //课程内容通过原因
                    handler.startElement("", "", "reason", null);
                    handler.characters(dataList.getReason().toCharArray(), 0, dataList.getReason().length());
                    handler.endElement("", "", "reason");
                }
                // 关闭节点
                handler.endElement("", "", "param");
                handler.endElement("", "", "msg");
                handler.endElement("", "", "fin");

                // 关闭文档
                handler.endDocument();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        return "9999";
    }
}
