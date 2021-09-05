/**
 * @Title: HttpContext
 * @Package http
 * @author wsw
 * @date 2021/8/31  20:04
 */
package http;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpContext {

    private static final Map<String,String> MIME_MAPPING = new HashMap<>();

    static {
        initMimeMapping();
    }

    private static void initMimeMapping() {

        try {
            SAXReader reader= new SAXReader();
            Document doc = reader.read(new File("./conf/web.xml"));
            Element root = doc.getRootElement();
            List<Element> list = root.elements("mime-mapping");
            for (Element element : list) {
                String extension= element.elementText("extension");
                String mime_type= element.elementText("mime-type");
                MIME_MAPPING.put(extension,mime_type);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    public static String getMimeType(String name){
        return MIME_MAPPING.get(name);
    }

}
