package day02;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用DOM解析xml文档
 */
public class ParseXmlDemo {
    public static void main(String[] args) {
        /*
        将emplist.xml文件中的所有员工信息读取出来
         */
        //用于存放员工对象
        List<Emp> empList = new ArrayList<>();
        /*
        使用DOM4J解析XML的大致步骤：
		  1:创建SAXReader
		  2:使用SAXReader读取指定的xml文档并生成Document对象
		  3:通过Document对象获取根元素
		  4:从根元素开始逐级获取子元素已达到遍历XML文档数据的目的
         */
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("./emplist.xml"));
            /*
            Document提供了方法：
            获取根元素: Element getRootElement()

            Element每一个实例用于表示xml实例文档中的一个元素(一对标签)
             常用方法:
              获取当前标签的名字: String getName()
              获取当前标签中间的文本信息: String getText()
              获取当前标签中指定名字的子标签: Element element(String name)
              获取当前标签中所有子标签: List elements()
              获取当前标签中所有同名的子标签(指定名字): List elements(String name)
              获取当前标签中指定名字的属性:Attribute attribute(String name)
              获取属性的值（Attribute提供）：String getValue()
             */
            Element root = doc.getRootElement();
            //获取跟标签下所有<emp>标签
            List<Element> list = root.elements("emp");
            for (Element empEle : list) {
                //获取员工姓名
                Element nameEle = empEle.element("name");
                String name = nameEle.getText();
                //获取性别
                String gender = empEle.element("gender").getText();
//                String gender = empEle.elementText("gender");
                //获取年龄
                String ageStr = empEle.element("age").getText();
//                String ageStr = empEle.elementText("age");
                int age = Integer.parseInt(ageStr);
                //获取工资
                int salary = Integer.parseInt(empEle.elementText("salary"));

                //获取id
//                Attribute attr = empEle.attribute("id");
//                int id = Integer.parseInt(attr.getValue());
//                int id = Integer.parseInt(empEle.attribute("id").getValue());
                int id = Integer.parseInt(empEle.attributeValue("id"));

                Emp emp = new Emp(id,name,gender,age,salary);
                empList.add(emp);
            }
            System.out.println("解析完毕");
            for (Emp emp : empList) {
                System.out.println(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}



