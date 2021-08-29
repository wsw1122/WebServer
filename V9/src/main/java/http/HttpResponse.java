package http;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 响应对象
 * 该类的每一个实例用于表示服务端发送给客户端的HTTP响应内容
 */
public class HttpResponse {
    /*
    状态行信息信息定义
     */
    //状态码，默认200
    private int statusCode = 200;
    //状态描述，默认“OK”
    private String statusReason = "OK";

    /*
    响应头相关信息定义
     */
    //key:响应头的名字  value：响应头对应的值
    private Map<String,String> headers = new HashMap<>();

    /*
    响应正文相关信息定义
     */
    //响应正文的实体文件
    private File entity;

    /*
    和连接先关信息定义
     */
    private Socket socket;
    private OutputStream out;

    /**
     * 实例化响应对象的同时将Socket传入，以便当前响应对象通过它来获取输出流
     * 给对应客户端发送响应内容。
     * @param socket
     */
    public HttpResponse(Socket socket){
        try{
            this.socket = socket;
            out = socket.getOutputStream();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 用于将当前乡音对象的内容以一个标准的HTTP响应格式发送给客户端
     */
    public void flush(){
        //顺序发送响应的三部分
        sendStatusLine();
        sendHeaders();
        sendContent();
    }

    /** 发送状态行 */
    private void sendStatusLine() {
        try{
            String line = "HTTP/1.1" + " " + statusCode + " " + statusReason;
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /** 发送响应头 */
    private void sendHeaders() {
        try{
            /*
            通过遍历headers，将所有需要发送给客户端的响应头进行发送
             */
            Set<Map.Entry<String,String>> entrySet = headers.entrySet();
            for(Map.Entry<String,String> e: entrySet){
                String name = e.getKey();
                String value = e.getValue();
                String line = name+": "+value;
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);
            }
            //单独发送CRLF表示响应头部分发送完毕
            out.write(13);
            out.write(10);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /** 发送响应正文(用户实际请求的资源数据)*/
    private void sendContent() {
        try{
            FileInputStream fis = new FileInputStream(entity);
            int len = -1;
            byte[] data = new byte[1024*10];
            while((len = fis.read(data)) != -1){
                out.write(data,0,len);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //提供entity的get/set方法
    public File getEntity() {
        return entity;
    }
    public void setEntity(File entity) {
        this.entity = entity;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusReason() {
        return statusReason;
    }
    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    /** 将给定的消息头设置到HttpResponse中*/
    public void putHeader(String key,String value){
        this.headers.put(key,value);
    }
    public String getHeader(String name){
        return this.headers.get(name);
    }

}














