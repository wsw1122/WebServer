package day01;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写程序，统计字符串中每个字符出现的次数
 * 使用Map保存统计结果，其中key保存出现的字符，value保存该字符出现的次数
 */
public class Test {
    public static void main(String[] args) {

        String str = "thinking in java!!";
        Map<Character,Integer> map = new HashMap<>();
        /*
            顺序获取字符串中的每一个字符
            先片段该字符作为key在Map中是否存在过？
            若存在，则说明该字符已经出现并统计过，那么对其对应的value值加1即可。
            若不存在，说明该字符没有统计过，那么将该字符作为key，而value保存数字1即可。
         */
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(map.containsKey(c)){
                //该字符统计过
                map.put(c,map.get(c)+1);
            }else{
                //该字符没有统计过
                map.put(c,1);
            }
        }
        System.out.println(map);
    }
}





