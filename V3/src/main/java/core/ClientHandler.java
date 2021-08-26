package core;

import http.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
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
            HttpRequest reqeust = new HttpRequest(socket);

            //处理请求

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
