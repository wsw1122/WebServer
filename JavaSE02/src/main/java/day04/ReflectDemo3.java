package day04;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 利用反射调用某个类的成员方法
 */
public class ReflectDemo3 {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        p.sayHello();

        Scanner scan = new Scanner(System.in);
        System.out.println("请输入类名:");
        String className = scan.nextLine();
        //利用反射
        Class cls = Class.forName(className);
        Object o = cls.newInstance();

        System.out.println("请输入方法名:");
        String methodName = scan.nextLine();
        //先通过类对象获取要调用的方法(对象)
        Method method = cls.getMethod(methodName);
        //调用指定对象的方法
        method.invoke(o);
    }
}




