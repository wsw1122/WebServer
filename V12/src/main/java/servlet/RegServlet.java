/**
 * @Title: RegServlet
 * @Package servlet
 * @author wsw
 * @date 2021/9/4  20:26
 */
package servlet;

import http.HttpRequest;
import http.HttpResponse;

public class RegServlet {

    public void service(HttpRequest request, HttpResponse response){
        System.out.println("RegServlet:处理注册业务....");

        String username = request.getParameters("username");


        System.out.println("RegServlet:注册完成....");
    }
}
