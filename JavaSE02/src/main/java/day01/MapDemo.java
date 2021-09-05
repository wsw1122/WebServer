package day01;

import java.util.HashMap;
import java.util.Map;

/**
 * java.util.Map 查找表
 *  Map是Java中非常经典数据结构之一
 *
 *  Map的结构看起来像是一个多行两列的表格，其中左列称为key，右列称为value
 *  Map总是以key-value对形式保存数据，并且Map提供了根据key获取其对应的value的查找方法。
 *
 *  Map有一个要求：key是不允许重复的（key的equals方法决定）
 *
 *  常用实现类：java.util.HashMap
 *  HashMap称为散列表或哈希表，使用散列算法实现的Map，是当今世界上查询速度最快的数据结构。
 *  其查询速度不受数据量影响，现网站的缓存都是用HashMap来实现。
 */
public class MapDemo {
    public static void main(String[] args) {

        Map<String,Integer> map = new HashMap<>();
        /*
        V put(K k,V v)
         将给定的key-value对保存到Map中。
         若给定的key在当前Map中已经存在，则是替换value操作，那么返回值就是
         原key对应的value。否则返回值为null。
         */
        Integer num = map.put("语文",95);
        System.out.println(num);//null
        map.put("数学",99);
        map.put("英语",92);
        map.put("物理",96);
        map.put("化学",95);
        System.out.println(map);
        num = map.put("语文",97);
        System.out.println(num);//95
        System.out.println(map);

        /*
        V get(Object key)
         根据给定的key获取对应的value，若给定的key在Map中不存在，则返回为null
         */
        num = map.get("数学");
        System.out.println("数学:"+num);
        num = map.get("体育");
        System.out.println("体育:"+num);

        int size = map.size();
        System.out.println("size:"+size);
        /*
        V remove(Object key)
         删除给定的key所对应的键值对，返回值为该key对应的value
         */
        num = map.remove("英语");
        System.out.println(num);
        System.out.println(map);

        /*
        boolean containsKey(Object key)
        boolean containsValue(Object value)
        判断当前Map是否包含给定的key或value
        是否包含还是一句元素自身equals比较的结果
         */
        boolean ck = map.containsKey("语文");
        System.out.println("包含key:"+ck);
        boolean cv = map.containsValue(93);
        System.out.println("包含value："+cv);

    }
}














