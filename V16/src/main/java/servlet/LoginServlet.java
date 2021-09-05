package servlet;

import http.HttpRequest;
import http.HttpResponse;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * 处理登录业务
 */
public class LoginServlet extends HttpServlet{
    public void service(HttpRequest request, HttpResponse response){
        System.out.println("LoginServlet: 处理登录业务...");
        //获取用户的登录信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //读取user.dat
        try(
                RandomAccessFile raf = new RandomAccessFile("user.dat","r");
                ){
            boolean check = false;//表示是否登录成功
            for (int i = 0; i < raf.length()/100; i++) {
                //先将指针移动至本次用户的起始位置
                raf.seek(i*100);
                //读取用户名
                byte[] data = new byte[32];
                raf.read(data);
                String name = new String(data,"utf-8").trim();
                if(name.equals(username)){//如果一致
                    raf.read(data);
                    String pwd = new String(data,"utf-8").trim();
                    if (pwd.equals(password)){
                        check = true;//登录成功
                        break;
                    }
                }
            }
            if(check){
                response.setEntity(new File("./webapps/myweb/login_success.html"));
            }else{
                response.setEntity(new File("./webapps/myweb/login_fail.html"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("LoginServlet: 登录业务完毕!!!");
    }
}
