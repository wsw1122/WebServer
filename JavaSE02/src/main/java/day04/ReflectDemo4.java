package day04;

import java.lang.reflect.Method;

/**
 * 调用有参的方法
 */
public class ReflectDemo4 {
    public static void main(String[] args) throws Exception {

        Person p = new Person();
        p.sayHello("苍老师");


        Class cls = Class.forName("day04.Person");
        Object o = cls.newInstance();//实例化

        //获取方法
        Method method = cls.getMethod("sayHello",String.class);
        method.invoke(o,"刘老师");

        Method m = cls.getMethod("sayHello", String.class, int.class);
        m.invoke(o,"小泽老师",18);


    }
}
class Aoo{
    /**
     * JDK5,java方法的列表里面，参数类型后可以跟... 这种写法为可变长度参数列表：
     *  可表参数列表的位置可以接受 0个或 1个或 多个这个类型的参数。
     *   注：可以是连续多个相同数据类型的参数，也可以是这个类型的数组
     *
     *   使用注意：
     *    1.编写 ... 的右侧空格可有可无
     *    2.一个方法最多只有1个可变参数
     *    3.必须是方法的最后一个参数（方法处理可变参数外，还可以在可变参数前 有其他的参数）
     *     public void show(int count,int... num){}
     */
    public void show(int... num){
        int sum = 0;
        for(int i=0;i<num.length;i++){
            sum += num[i];
        }
        System.out.println("sum:"+sum);
    }

}







