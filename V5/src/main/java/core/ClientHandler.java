package core;


import http.HttpRequest;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

/**
 * 客户端处理器，负责处理与客户端的交互工作
 */
public class ClientHandler implements Runnable{

    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run() {
        try {
            //解析请求
            //实例化请求对象，实例化过程也是解析请求的过程
            HttpRequest reqeust = new HttpRequest(socket);

            //处理请求
            String path = reqeust.getUrl();
            File file = new File("./webapps"+path);
            if (file.exists()){
                System.out.println("资源已找到");
                OutputStream out = socket.getOutputStream();
                String line = "HTTP/1.1 200 OK";
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);

                line = "Content-Type: text/html";
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);

                line = "Content-Length: "+file.length();
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);
                out.write(13);
                out.write(10);


                FileInputStream fis = new FileInputStream(file);
                int len = -1;
                byte[] data = new byte[1024*10];
                while ((len = fis.read(data)) != -1){
                    out.write(data,0,len);
                }

            }else {
                System.out.println("资源没找到");
            }
            //发送响应


        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //处理完请求并响应客户端后与其断开连接
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
