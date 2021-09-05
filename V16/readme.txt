本版本中我们利用反射来完成所有Servlet的初始化操作，
并使得ClientHandler不用再每次添加新的业务后都要添加分支。

我们可以设计一个Map，其中key为请求，而value为处理该
请求的Servlet实例。然后ClientHandler在处理请求的环节
首先通过请求的路径在这个Map中判定是否为处理业务，若
是则获取其对应的Servlet并调用其service方法进行处理。
（反射）而Map的初始化可以通过配置文件来配置，将请求与对应的
Servlet的名字以一个xml文件形式保存。我们将请求作为
key，将Servlet的名字得到后利用反射进行实例化并作为value即可。


实现:
1:在core中添加一个新的类:
  ServerContext
    使用这个类来保存所有服务端配置信息，将来所有会变化的
    内容都放在这里，然后将值作为配置文件中的内容进行管理
    这里暂时只定义第一个:所有的Servlet。

2:在ServerContext中定义一个属性:Map servlets
    其中key保存所有的请求，value保存处理对应请求的Servlet实例

3:定义初始化方法，并在静态块中调用，确保ServerContext加载时进行初始化操作

4:对外提供一个根据请求获取对应Servlet实例的方法

5:在ClientHandler处理请求的环节，根据请求路径去
  ServerContext中获取对应的Servlet，若获取到则
    调用其service方法处理。没获取到说明这个请求不是
    请求业务，那么当静态资源做后续处理。


6(反射):在config目录下新建一个配置文件:servlets.xml
    并将所有请求与对应的Servlet名字配置在这里

7(反射):在ServerContext初始化中加载servlets.xml，并利用
    反射将Servlet实例化出来。
