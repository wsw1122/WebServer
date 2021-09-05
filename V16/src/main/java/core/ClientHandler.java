package core;

import http.EmptyRequestException;
import http.HttpContext;
import http.HttpRequest;
import http.HttpResponse;
import servlet.HttpServlet;
import servlet.LoginServlet;
import servlet.RegServlet;

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
            String path = request.getRequestURI();
            /*
            首先判断该请求路径是否为一个业务，根据请求路径去ServerContext提取对应的Servlet，
            若返回值不为null，则说明该请求是一个业务，那么就调用对应Servlet的service方法即可。
             */
            HttpServlet servlet = ServerContext.getServlet(path);
            if(servlet != null){
                servlet.service(request,response);

            }else{
                //从webapps目录下根据抽象路径寻找请求资源
                File file = new File("./webapps"+path);

                //判断用户请求的资源是否存在
                if(file.exists()){
                    System.out.println("该资源已找到");
                    //将要响应的资源设置到response中
                    response.setEntity(file);
                }else{
                    System.out.println("该资源不存在");
                    File f = new File("./webapps/root/404.html");
                    //响应404页面
                    response.setEntity(f);
                    //设置状态码、状态描述
                    response.setStatusCode(404);
                    response.setStatusReason("NOT FOUND");
                }
            }
            //发送响应
            response.flush();


        } catch(EmptyRequestException e){
            System.out.println("空请求...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            //处理完请求并响应客户端后与其断开连接
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
