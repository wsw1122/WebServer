/**
 * @Title: HttpContext
 * @Package http
 * @author wsw
 * @date 2021/8/31  20:04
 */
package http;

import java.util.HashMap;
import java.util.Map;

public class HttpContext {

    private static final Map<String,String> MIME_MAPPING = new HashMap<>();

    static {
        initMimeMapping();
    }

    private static void initMimeMapping() {
        MIME_MAPPING.put("html","text/html");
        MIME_MAPPING.put("css","text/css");
        MIME_MAPPING.put("js","application/javascript");
        MIME_MAPPING.put("png","image/png");
        MIME_MAPPING.put("jpg","image/jpeg");
        MIME_MAPPING.put("gif","image/gif");
    }

    public static String getMimeType(String name){
        return MIME_MAPPING.get(name);
    }

}
