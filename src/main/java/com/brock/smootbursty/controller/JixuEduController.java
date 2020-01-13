package com.brock.smootbursty.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import com.brock.smootbursty.model.Book;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/edu")
public class JixuEduController {


    private List<Book> bookList;

    @GetMapping("/createXML")
    public String createXML(){

        List<Book> list = new ArrayList<>();
        Book book = new Book();
        book.setAuthor("hutao");
        book.setId(1L);
        book.setName("胡涛");
        book.setPrice(0.32f);
        list.add(book);

        File dest = new File("D:\\Users\\Administrator\\Desktop\\person1.xml");

        createXMLBySAX(list,dest);
        return "成功";

    }

    //生成XML文件
    public void createXMLBySAX(List<Book> books, File dest) {
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
                handler.startElement("", "", "bookstore", null);
                AttributesImpl atts = new AttributesImpl();
                for (Book book : books) {
                    atts.clear();
                    atts.addAttribute("", "", "id", "", String.valueOf(book.getId()));
                    handler.startElement("", "", "book", atts);
                    handler.startElement("", "", "name", null);
                    handler.characters(book.getName().toCharArray(), 0, book.getName().length());
                    handler.endElement("", "", "name");
                    handler.startElement("", "", "author", null);
                    handler.characters(book.getAuthor().toCharArray(), 0, book.getAuthor().length());
                    handler.endElement("", "", "author");
                    handler.startElement("", "", "price", null);
                    handler.characters(Float.toString(book.getPrice()).toCharArray(), 0,
                            Float.toString(book.getPrice()).length());
                    handler.endElement("", "", "price");
                    handler.endElement("", "", "book");
                }
                // 关闭节点
                handler.endElement("", "", "bookstore");
                // 关闭文档
                handler.endDocument();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    //解析XML文件
    /*public List<Book> parseXMLBySAX(File file) {
        SAXParser parser = getParser();
        MyHandler handler = new MyHandler();
        try {
            parser.parse(file, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookList = handler.getBookList();

        return bookList;
    }

    public SAXParser getParser() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return parser;
    }*/



}
