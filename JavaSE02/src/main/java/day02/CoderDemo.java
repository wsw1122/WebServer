package day02;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 字符集转换
 */
public class CoderDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "刘苍松";
        String s = URLEncoder.encode(str,"utf-8");
        System.out.println(s);//有规律的编码

        String s1 = URLDecoder.decode(s,"utf-8");
        System.out.println(s1);

    }
}




