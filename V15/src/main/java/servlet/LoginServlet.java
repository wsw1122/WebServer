package servlet;

import http.HttpRequest;
import http.HttpResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 处理登录业务
 */
public class LoginServlet extends HttpServlet{
    public void service(HttpRequest request, HttpResponse response){
        System.out.println("LoginServlet: 处理登录业务...");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (   RandomAccessFile raf = new RandomAccessFile("user.dat","r");
        ){
            boolean check = false;
            for (int i = 0; i < raf.length()/100; i++) {
                raf.seek(i*100);

                byte[] data = new byte[32];
                raf.read(data);
                String name =  new String(data,"utf-8").trim();
                if(name.equals(username)){
                    raf.read(data);
                    String pwd = new String(data,"utf-8").trim();
                    if (pwd.equals(password)){
                        check = true;
                        break;
                    }
                }
            }

            if (check){
                response.setEntity(new File("./webapps/myweb/login_success.html"));
            }else {
                response.setEntity(new File("./webapps/myweb/login_fail.html"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("LoginServlet: 登录业务完毕!!!");
    }
}
