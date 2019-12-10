package com.example.admin.job_4_1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * 主要dom解析XML的文件数据
 * 方便做节查找
 * Created by admin on 2018/11/19.
 */

public class ChinaXMLService {

    /**
     * @ NodeList ROOTNODELIST二级节点
     * @NodeList CITYNODELIST三级节点
     * @List<String> weatherCodeList三级节点对于的weatherCode值
     */
    private static NodeList ROOTNODELIST ;
    private static NodeList CITYNODELIST ;
    public static List<String> weatherCodeList;

    /**
     * 通过输入流的得到一个省和直辖市的List结合
     * @param is
     * @return
     */
    public static List<String> getRoot(InputStream is){
        List<String> list = new ArrayList<String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("province");
            for (int i=0; i<nodeList.getLength(); i++){
                Element  element = (Element) nodeList.item(i);
                String province = element.getAttribute("name");
                list.add(province);
            }
            ROOTNODELIST = nodeList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param string
     * @return
     */
    public static List<String> getRootCity(String string){
        List<String> list = new ArrayList<String>();
        for (int i=0; i<ROOTNODELIST.getLength();i++){
            Element element = (Element) ROOTNODELIST.item(i);
            String rootName = element.getAttribute("name");
            if (rootName.equals(string)){
                NodeList nodeList = element.getElementsByTagName("city");
                for (int j=0; j<nodeList.getLength(); j++){
                    String string1 = null;
                    Element element1 = (Element) nodeList.item(j);
                    string1 = element1.getAttribute("name");
                    list.add(string1);
                }
                CITYNODELIST = nodeList;
            }
        }
        return list;
    }
    public static List<String> getCounty(String county){
        weatherCodeList = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        for (int i=0; i<CITYNODELIST.getLength();i++){
            Element element = (Element) CITYNODELIST.item(i);
            String rootName = element.getAttribute("name");
            if (rootName.equals(county)){
                NodeList nodeList = element.getElementsByTagName("county");
                for (int j=0; j<nodeList.getLength(); j++){
                    String string1 = null;
                    String string2 = null;
                    Element element1 = (Element) nodeList.item(j);
                    string1 = element1.getAttribute("name");
                    string2 = element1.getAttribute("weatherCode");
                    list.add(string1);
                    weatherCodeList.add(string2);
                }
            }
        }
        return list;
    }
}
