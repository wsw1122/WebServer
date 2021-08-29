/**
 * @Title: HttpRespense
 * @Package http
 * @author wsw
 * @date 2021/8/29  14:10
 */
package http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpResponse {
    private File entity;
    private Socket socket;
    private OutputStream out;
    private String line;
    public HttpResponse(Socket socket){
        try {
            this.socket = socket;
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush(){
        sendStatusLine();
        sendHeaders();
        sendContent();
    }
    //发送状态行
    private void sendStatusLine() {
        try {
            String line = "HTTP/1.1 200 OK";
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //发送头
    private void sendHeaders() {
        try {
            line = "Content-Type: text/html";
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);
            line = "Content-Length: "+entity.length();
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);
            out.write(13);
            out.write(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //发送内容
    private void sendContent() {
        try {
            FileInputStream fis = new FileInputStream(entity);
            int len = -1;
            byte[] data = new byte[1024*10];
            while ((len = fis.read(data)) != -1){
                out.write(data,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getEntity() {
        return entity;
    }

    public void setEntity(File entity) {
        this.entity = entity;
    }


}
