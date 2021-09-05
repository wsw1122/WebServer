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