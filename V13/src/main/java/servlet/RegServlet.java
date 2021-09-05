package servlet;

import http.HttpRequest;
import http.HttpResponse;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 处理注册业务
 */
public class RegServlet {

    public void service(HttpRequest request, HttpResponse response){
        System.out.println("RegServlet:处理注册业务...");
        /*
            1.通过request获取用户在页面上输入的注册信息
            2.将该用户信息写入到文件user.dat中保存起来
            3.响应客户端注册成功页面
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println(username+","+password+","+nickname+","+age);
        /*
        将用户信息写入user.dat中，每个用户占用100字节，其中用户名、密码、昵称各占32字节
        年龄是int类型占4字节
         */
        try(
                RandomAccessFile raf = new RandomAccessFile("user.dat","rw");
                ){
            raf.seek(raf.length());//将指针移动文件末尾
            //写出用户名、密码、昵称
            byte[] data = username.getBytes("utf-8");
            data = Arrays.copyOf(data,32);
            raf.write(data);
            data = password.getBytes("utf-8");
            data = Arrays.copyOf(data,32);
            raf.write(data);
            data = nickname.getBytes("utf-8");
            data = Arrays.copyOf(data,32);
            raf.write(data);
            //写出年龄
            raf.writeInt(age);

            //设置response，响应注册成功页面
            response.setEntity(new File("./webapps/myweb/reg_success.html"));

        }catch (Exception e){
            e.printStackTrace();
        }





        System.out.println("RegServlet:注册业务完毕!!!");
    }


}
