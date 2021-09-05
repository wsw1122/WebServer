package day03;

import java.lang.reflect.Method;

/**
 * Java反射机制
 *  在运行状态中，对于任意一个类的，都能知道这个类中的所有属性和方法，
 *  对于任意一个对象，都能调用它的任意的一个方法和属性，这种动态获取的信息
 *  以及动态调用对象方法的功能称为Java反射。
 *
 *  Java反射机制是一种动态机制，允许我们加载一个类，实例化一个类，调用方法(属性)
 *  从编译器转换为运行期确定。
 *
 *  反射由于更大的资源开销，不能过度依赖反射。
 */
public class ReflectDemo1 {
    public static void main(String[] args) {

        String s1 = new String("hello");
        /*
        Class类(类对象)
         Class类的每一个实例用于表示JVM已经加载的一个类。并且JVM内部每个被加载的类
         都有且只有一个Class的实例与之对应。
         通过Class类我们可以：
          获取类的名字，构造方法(快速实例化)，属性(调用)，方法(调用)

          获取一个类的类对象有以下方式：(String的类对象)
           1.直接通过类的静态属性class得到。
            Class cls = String.class

           2.通过Class提供的静态方法forName()加载
            Class cls = Class.forName("java.lang.String");

           3.通过类加载器( ClassLoader )
         */

        try {

            Class cls = Class.forName("java.lang.String");
            String name = cls.getName();
            System.out.println(name);
            //获取所有方法（含从超类继承过来的方法）
            Method[] methods = cls.getMethods();
            //获取本类定义的方法（不含父类继承的方法）
//            Method[] methods = cls.getDeclaredMethods();
            System.out.println(methods.length);
            for(Method method : methods){
                System.out.println(method.getName());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}




