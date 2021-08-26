package core;

import java.io.BufferedReader;
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
            //读取客户端发送过来的内容
            InputStream in = socket.getInputStream();
            StringBuilder builder = new StringBuilder();
            int c1 = -1,c2=-1;
            while((c2=in.read()) != -1){
                if(c1==13 && c2==10){
                    break;
                }
                builder.append((char) c2);
                c1 = c2;
            }
            String line = builder.toString().trim();
            System.out.println(line);
        } catch (IOException e) {
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
