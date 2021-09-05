package http;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP协议相关协议内容
 *  设计这个类的目的是将所有的HTTP协议定义的内容都放在这里。
 *  这样我们无论那个类需要用到HTTP协议定义的东西时，都可以来这里找到。
 */
public class HttpContext {
    /**
     * Content-Type与资源类型的映射
     * key： 资源类型（后缀名）
     * value：Content-Type对应的值
     */
    private static final Map<String,String> MIME_MAPPING = new HashMap<>();

    static{
        initMimeMapping();
    }
    //初始化MIME_MAPPING
    private static void initMimeMapping() {
        /*
        通过解析conf/web.xml文件，将所有的类型初始化出出来
         1.创建SAXReader并读取conf/web.xml文件
         2.将根元素下所有名为：<mime-mapping>的标签出来
         3.遍历所有的<mime-mapping>标签：获取子标签
          <extension>中间的文本作为key
          <mime-type>中文的文本作为value
         保存到MIME_MAPPING中即可。
         */
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("conf/web.xml"));
            Element root = doc.getRootElement();
            List<Element> mimeList = root.elements("mime-mapping");
            for(Element mimeEle:mimeList){
                String key = mimeEle.elementText("extension");
                String value = mimeEle.elementText("mime-type");
                MIME_MAPPING.put(key,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 根据资源类型名获取对应的Content-Type值
     * @param name 类型资源名(css、png、html)
     */
    public static String getMimeType(String name){
        return MIME_MAPPING.get(name);
    }

    public static void main(String[] args) {
        String fileName = "xxx.xxx.xx.css";
        int index = fileName.lastIndexOf(".")+1;
        String name = fileName.substring(index);

        String line = HttpContext.getMimeType(name);
        System.out.println(line);//text/css
    }

}
