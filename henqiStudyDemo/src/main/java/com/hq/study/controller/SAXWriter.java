package com.hq.study.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


public class SAXWriter {

    public static void main(String[] args) throws FileNotFoundException, TransformerConfigurationException, SAXException{
        Result resultXml = new StreamResult(new FileOutputStream("D:\\Users\\Administrator\\Desktop\\person.xml")); //输出到person.xml
        SAXTransformerFactory sff = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
        TransformerHandler th = sff.newTransformerHandler();
        th.setResult(resultXml);

        Transformer transformer = th.getTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //编码格式是UTF-8
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //换行
        th.startDocument(); //开始xml文档
        AttributesImpl attr = new AttributesImpl();
        th.startElement("", "", "person", attr); //定义person节点
        th.startElement("", "", "name", attr); //定义name节点
        th.characters("张三".toCharArray(), 0, "张三".length());
        th.endElement("", "", "name"); //结束name节点
        th.startElement("", "", "age", attr); //定义age节点
        th.characters("29".toCharArray(), 0, "29".length());
        th.endElement("", "", "age"); //结束age节点
        th.startElement("", "", "gender", attr); //定义gender节点
        th.characters("男".toCharArray(), 0, "男".length());
        th.endElement("", "", "gender"); //结束gender节点
        th.endElement("", "", "person"); //结束person节点
        th.endDocument(); //结束xml文档
    }
}
