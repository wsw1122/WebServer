package day03;

import java.util.Scanner;

/**
 * 使用类对象快速实例化对象
 */
public class ReflectDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Person p = new Person();
        System.out.println(p);

        /*
        1.加载需要实例化对象的类的类对象
        2.通过类对象进行实例化
         */
        System.out.println("请输入你想要实例化的类名：(全限定名:包名.类名)");
        Scanner scan = new Scanner(System.in);
        String className = scan.nextLine();
        Class cls = Class.forName(className);
        /*
        Class 提供的方法：
          Object newInstance()
           这个方法要求Class所表示的类必须有无参构造方法，否则会抛出异常。
           若当前类没有无参构造方法，我们需要先通过类对象获取其定义的某种构造方法，然后
           通过 构造方法对象 来进行实例化。
         */
        Object obj = cls.newInstance();
        System.out.println(obj);

    }
}





