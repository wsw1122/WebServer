package core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import servlet.HttpServlet;
import servlet.LoginServlet;
import servlet.RegServlet;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务端所有的配置信息
 */
public class ServerContext {
    /*
    所有Servlet：
     key：请求路径
     value：具体处理对应业务的Servlet实例
     */
    private static Map<String, HttpServlet> SERVLET_MAPPING = new HashMap<>();

    static{
        initServletMapping();
    }

    /** 初始化所有的Servlet*/
    private static void initServletMapping(){
//        SERVLET_MAPPING.put("/myweb/reg", new RegServlet());
//        SERVLET_MAPPING.put("/myweb/login", new LoginServlet());

        /*
         解析conf/servlets.xml，通过根标签获取所有servlet标签
         属性path作为key，属性className的值取出利用反射实例化得到对应的Servlet实例作为value
         保存到SERVLET_MAPPING这个Map完成出初始化。

         注：利用反射实例化Servlet后，返回的是Object，但是这些Servlet都继承自HttpServlet，
         所以将它们造型(强转)成HttpServlet即可，然后以value形式存入Map。
         */
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("conf/servlets.xml"));
            Element root = doc.getRootElement();
            List<Element> servletList = root.elements();
            for(Element servletEle : servletList){
                String key = servletEle.attributeValue("path");
                String className = servletEle.attributeValue("className");
                Class cls = Class.forName(className);
                HttpServlet value = (HttpServlet) cls.newInstance();
                SERVLET_MAPPING.put(key,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /** 根据请求路径获取对应的Servlet实例*/
    public static HttpServlet getServlet(String path){
        return SERVLET_MAPPING.get(path);
    }

    //测试
    public static void main(String[] args) {
        HttpServlet servlet = ServerContext.getServlet("/myweb/login");
        System.out.println(servlet);
    }


}






