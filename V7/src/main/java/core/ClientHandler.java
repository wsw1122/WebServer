package core;


import http.HttpRequest;
import http.HttpResponse;

import java.io.*;
import java.net.Socket;

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
            HttpRequest request = new HttpRequest(socket);
            HttpResponse response = new HttpResponse(socket);
            //处理请求
            String path = request.getUrl();
            File file = new File("./webapps"+path);
            if (file.exists()){
                System.out.println("资源已找到");
                response.setEntity(file);
                response.flush();




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
