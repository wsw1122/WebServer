package http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

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
    /*
    消息头相关信息定义
     */

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
            String line = builder.toString().trim();
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

    }

    /**
     * 解析消息正文
     */
    private void parseContent() {
    }
}
