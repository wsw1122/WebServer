package day01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Map的遍历：(3种)
 *  遍历所有的key
 *  遍历所有的key-value对
 *  遍历所有的value(不常用)
 */
public class MapDemo2 {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("语文",97);
        map.put("数学",96);
        map.put("英语",94);
        map.put("物理",99);
        System.out.println(map);

        /*
        Set keySet()
         将当前Map中所有的key以一个Set集合形式返回
         */
        Set<String> keySet = map.keySet();
        for (String key : keySet){
            System.out.println("key:"+key);
        }

        /*
        Set<Entry> entrySet()
         将当前Map中每一组键值对一个Entry实例形式存放于Set集合后返回
        java.util.Map.Entry
         Entry的每一个实例用于表示Map中的一组键值对
         */
        Set<Entry<String,Integer>> entrySet = map.entrySet();
        for (Entry<String,Integer> entry : entrySet){
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key+":"+value);
        }

        /*
        Collection values()
         将当前Map中所有的value以一个Collection集合的形式返回。
         Map中的value是可以有重复的，所以没有用Set集合返回。
         */
        Collection<Integer> c = map.values();
        for (Integer i : c){
            System.out.println("value:"+i);
        }
    }
}




