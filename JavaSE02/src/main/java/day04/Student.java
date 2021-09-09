package day04;

public class Student {
    private String name;
    public int age;

    public Student(){}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void show(){
        System.out.println("Student的show方法");
    }

    public String say(String name,int age,Integer sal){
        return "你好，"+name;
    }

}
