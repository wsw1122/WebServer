package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * WebServer主类
 *  启动这个类，开启WebServer主类
 *
 *  这个程序的功能是启动之后，可以使用浏览器来发送请求，
 *  我们编写代码对浏览器的请求作出响应的效果
 */
public class WebServer {

    private ServerSocket server;

    public WebServer(){
        try {
            System.out.println("正在启动服务端...");
            server = new ServerSocket(8088);
            System.out.println("服务端启动完毕!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            //             使用的协议/服务器地址信息/抽象路径部分
            //浏览器进行测试: http://localhost:8088/index.html
            System.out.println("等待客户端连接...");
            Socket socket = server.accept();
            System.out.println("一个客户端连接了!!");

            //启动线程，处理客户端交互
            ClientHandler handler = new ClientHandler(socket);
            Thread t = new Thread(handler);
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebServer server = new WebServer();
        server.start();
    }

}











