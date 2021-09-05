package servlet;

import http.HttpRequest;
import http.HttpResponse;

/**
 * 所有Servlet的超类
 */
public abstract class HttpServlet {
    public abstract void service(HttpRequest request, HttpResponse response);
}
