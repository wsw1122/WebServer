package day04;

import java.lang.reflect.Method;

/**
 * 使用反射调用私有方法
 */
public class ReflectDemo5 {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
//        p.say();//私有方法

        Class cls = Class.forName("day04.Person");
        Object o = cls.newInstance();


        //在类的外部通过反射调用私有方法会破坏封装性，不是必要操作，不建议这样做
        Method m = cls.getDeclaredMethod("say");

        //在访问私有方法前 进行设置访问操作(不设置会报错)
        m.setAccessible(true);
        m.invoke(o);


    }
}



