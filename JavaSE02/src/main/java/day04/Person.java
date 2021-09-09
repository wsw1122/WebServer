package day04;

/**
 * 使用这个类测试反射机制
 */
public class Person {

    public void sayHello(){
        System.out.println("hello!");
    }
    public void sayHi(){
        System.out.println("hi!");
    }
    public void sayHello(String name){
        System.out.println("hello!"+name);
    }
    public void sayHello(String name,int age){
        System.out.println("hello!我叫"+name+"，今年"+age+"岁了");
    }

    private void say(){
        System.out.println("Person的私有方法!");
    }

}







