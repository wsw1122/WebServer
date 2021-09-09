package day04;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StudentReflectDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Class cls = Student.class;
        //解析这个类
        /*
        Field[] cls.getFields() 获取能访问到的属性
        Field[] cls.getDeclaredFields() 获取所有属性

        String f.getName() 获取属性名字
        Class<?> f.getType() 获取属性的类型的Class对象
         */
        Field[] fs = cls.getDeclaredFields();
        for (Field f : fs){
            System.out.println(f.getName());
            System.out.println(f.getType().getName());
        }
        System.out.println("------------");
        /*
        Constructor[] cls.getConstructors() 获取能访问到的构造
        Constructor[] cls.getDeclaredConstructors() 获取能访问到的构造

        String c.getName() 获取构造名(类名--全限定名)
        Class<?>[] c.getParameterTypes() 获取参数的Class类型数组
         */
        Constructor[] cs = cls.getConstructors();
        for(Constructor c : cs){
            System.out.println(c.getName());
            Class[] clss = c.getParameterTypes();
            for(Class cc : clss){
                System.out.println(cc.getName());
            }
        }
        System.out.println("--------------");
        /*
        cls.getMethods() 获取能访问的方法（包含父类）
        cls.getDeclaredMethods() 获取本类声明的方法(不含父类)

        m.getName() 获取方法名
        m.getReturnType() 获取方法的返回值类型
        m.getParameterTypes() 获取方法所有的参数Class类型的数组
         */
        Method[] ms = cls.getDeclaredMethods();
        for(Method m : ms){
            System.out.println(m.getName());
            System.out.println(m.getReturnType().getName());
            Class[] clss = m.getParameterTypes();
            for(Class cc : clss){
                System.out.println(cc.getName());
            }
        }

        Student stu = (Student)cls.newInstance();
        stu.age = 18;
        stu.show();

    }
}









