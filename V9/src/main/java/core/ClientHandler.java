package core;

import http.HttpContext;
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
            //实例化响应对象
            HttpResponse response = new HttpResponse(socket);

            //处理请求
            //先通过request获取用户请求的资源的抽象路径
            String path = request.getUrl();
            //从webapps目录下根据抽象路径寻找请求资源
            //http://localhost:8088/myweb/index.html
            File file = new File("./webapps"+path);
            //判断用户请求的资源是否存在
            if(file.exists()){
                System.out.println("该资源已找到");
                //将要响应的资源设置到response中
                response.setEntity(file);
                //设置响应头
                String fileName = file.getName();
                int index = fileName.lastIndexOf(".")+1;
                String name = fileName.substring(index);
                System.out.println(name);
                String line = HttpContext.getMimeType(name);
                response.putHeader("Content-Type",line);
                response.putHeader("Content-Length",file.length()+"");

            }else{
                System.out.println("该资源不存在");
                File f = new File("./webapps/root/404.html");
                //响应404页面
                response.setEntity(f);
                //设置状态码、状态描述
                response.setStatusCode(404);
                response.setStatusReason("NOT FOUND");
                //设置响应头
                response.putHeader("Content-Type","text/html");
                response.putHeader("Content-Length",f.length()+"");

            }
            //发送响应
            response.flush();


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
