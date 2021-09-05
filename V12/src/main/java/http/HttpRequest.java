package http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求对象：该类的每一个实例用于表示一个HTTP请求内容
 * <p>
 * 一个HTTP请求包含三部分：请求行、消息头、消息正文
 */
public class HttpRequest {
    /*
    请求行相关信息定义
     */
    private String method;  //请求方式
    private String url;     //请求资源的抽象路径
    private String protocol;//请求使用HTTP协议版本

    private String requestURI;



    private String queryString;

    private Map<String,String> parameters = new HashMap<>();
    /*
    消息头相关信息定义
     */
    private Map<String,String> headers = new HashMap<>();

    /*
    消息正文相关信息定义
     */

    /*
    和连接相关信息定义
     */
    private Socket socket;
    private InputStream in;

    /**
     * 构造方法，用来初始化请求对象
     */
    public HttpRequest(Socket socket) {
        try {
            this.socket = socket;
            in = socket.getInputStream();//读取客户端发送过来的内容
            /*
            1.调用解析请求行
            2.调用解析消息头
            3.调用解析消息正文
             */
            parseRequestLine();
            parseHeaders();
            parseContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析请求行
     */
    private void parseRequestLine() {
        System.out.println("HttpRequest:正在解析请求行...");
        try {
            String line = readLine();
            System.out.println("请求行:"+line);

            /*
            这里后期可以循环接收客户端请求时会偶尔出现下标越界问题。
            主要原因是因为空请求问题，后期解决。
             */
            String[] data = line.split("\\s");
            method = data[0];
            url = data[1];//后期这里可能会越界
            protocol = data[2];

            System.out.println("method:"+method);
            System.out.println("url:"+url);
            System.out.println("protocol:"+protocol);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HttpRequest:解析请求行完毕!!!");
    }

    /**
     * 解析消息头
     */
    private void parseHeaders() {
        System.out.println("HttpRequest:正在解析消息头...");
        /*
        parseRequestLine()已经通过输入流将请求中的请求行内容读取完毕，那么
        到 parseHeaders()时再通过输入流读取的内容就是消息头部分了。
        解析思路：
         1.顺序读取若干行字符串，每一行都是一个消息头。将消息头按照“冒号空格”
         (: )拆分为两部分，分别是消息头的名字与对应的值。
         2.并将每一个消息头的名字作为key，消息头的值作为value保存headers这个
         Map中即可完成解析工作。
         3.如果读取一行字符串是返回的是一个空字符串，即:“”,则说明本次单独读取到了
         CRLF，那么就可以停止读取工作了（消息头读取完毕。）
         */
        try{
            while(true){
                String line = readLine();
                if("".equals(line)){
                    break;
                }
                String[] data = line.split(": ");
                headers.put(data[0],data[1]);
            }
            System.out.println("headers:"+headers);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(url.indexOf("?") == -1){
            this.requestURI = url;
        }else {
            String[] data = url.split("\\?");
            this.requestURI = data[0];
            if(data.length>1){
                this.queryString = data[1];
                data = queryString.split("&");
                for (String paraStr : data) {
                    String[] arr = paraStr.split("=");
                    if (arr.length>1){
                        this.parameters.put(arr[0],arr[1]);
                    }else {
                        this.parameters.put(arr[0],null);
                    }
                }
            }
        }
        System.out.println("requestURIL:"+requestURI);
        System.out.println("queryString:"+queryString);
        System.out.println("parameters:"+parameters);
        System.out.println("HttpRequest:解析消息头完毕!!!");
    }

    /**
     * 通过对应客户端的输入流，读取一行客户端发送过来的字符串，
     * 一行是以(CRLF)作为结束的。
     * @return
     */
    private String readLine() throws IOException {
        StringBuilder builder = new StringBuilder();
        //c1表示上一次读到的字符，c2代表本次读取到的字符
        int c1 = -1, c2 = -1;
        while (true) {
            if (!((c2 = in.read()) != -1)) break;
            //如果连续读取的两个字符为CR,LF就停止读取
            if (c1 == 13 && c2 == 10) {
                break;
            }
            builder.append((char) c2);
            //把c2赋值给c1作为下次循环的上一个字符
            c1 = c2;
        }
        return builder.toString().trim();
    }

    /**
     * 解析消息正文
     */
    private void parseContent() {
    }

    /*
    这个HttpRequest对应属性对外仅提供get方法。
    因为请求内容是客户端发送过来的，所以不需要做其他改动，应此对外只读即可。
     */

    public String getMethod() {
        return method;
    }
    public String getUrl() {
        return url;
    }
    public String getProtocol() {
        return protocol;
    }
    public String getHeader(String name){
        return headers.get(name);
    }
    public String getRequestURI() {
        return requestURI;
    }
    public String getQueryString() {
        return queryString;
    }

    public String getParameters(String name) {
        return parameters.get(name);
    }
}
