package com.hexq.qh.common.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XML工具类
 * @author hexq
 * @date 2017/11/28  17:01
 */
public class XmlUtil {

    /**
     * 使用DOM4J把原始的XML转换成Map，key为节点的路径，值为节点值
     * @param xml XML
     * @return Map
     */
    public static Map<String, String> xml2Map(String xml) {
        Map<String, String> map = new HashMap<>();
        try {
            //转为Document对象
            Document document = DocumentHelper.parseText(xml);
            //根节点
            Element nodeElement = document.getRootElement();
            List nodes = nodeElement.elements();
            //遍历节点放入Map
            for (Object node : nodes) {
                Element ele = (Element) node;
                map.put(ele.getName(), ele.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 把XML的节点元素转为Map
     * @return Map
     */
    public static Map<String, String> xmlElement2Map(String xmlStr) {
        xmlStr = "<ns2: xmlns:ns2=\"http://www.w3.org/1999/XSL/Transform\">" + xmlStr + "</ns2:>";
        return xml2Map(xmlStr);
    }

    /**
     * 把map转换成xml
     * @param map Map
     * @return XML
     */
    public static String map2Xml(Map<String, Object> map) {
        //创建Document
        Document document = DocumentHelper.createDocument();
        //在Document下添加根节点
        Element nodeElement = document.addElement("params");
        //在根节点中添加子节点
        for (String str : map.keySet()) {
            Element keyElement = nodeElement.addElement(str);
            keyElement.setText(String.valueOf(map.get(str)));
        }
        String xmlStr = "";
        XMLWriter writer = null;
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            //XML格式化
            OutputFormat format = new OutputFormat("", false, "UTF-8");
            writer = new XMLWriter(out, format);
            //将Document对象转为文本
            writer.write(document);
            xmlStr = out.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return xmlStr;
    }

    /**
     * 通过JAXB把bean转换成XML
     * @param object bean对象
     * @return XML
     */
    @SuppressWarnings("unchecked")
    public static String bean2Xml(Object object) {
        StringWriter sw = null;
        String xmlStr = "";
        try {
            //创建JAXB上下文
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            //创建QName，就不用对bean对象使用@XmlRootElement注解
            QName qName = new QName("http://www.w3.org/1999/XSL/Transform", object.getClass().getSimpleName());
            JAXBElement jaxbPerson = new JAXBElement(qName, object.getClass(), object);
            //XML装配器
            Marshaller marshaller = context.createMarshaller();
            //格式化xml格式
            //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //去掉生成xml的默认报文头
            //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            sw = new StringWriter();
            marshaller.marshal(jaxbPerson, sw);
            xmlStr = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return xmlStr;
    }
}
