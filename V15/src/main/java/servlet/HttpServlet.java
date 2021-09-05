/**
 * @Title: HttpServlet
 * @Package servlet
 * @author wsw
 * @date 2021/9/5  11:22
 */
package servlet;

import http.HttpRequest;
import http.HttpResponse;

public abstract class HttpServlet {
    public abstract void service(HttpRequest request, HttpResponse response);
}
