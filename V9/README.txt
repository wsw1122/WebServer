本版本对页面显示图片等资源的支持
在V8中已经实现了响应用户请求的资源操作，但是
无论用户请求的资源是何种类型，我们在响应时，
指定资源类型的响应头:Content-Type给定的值都
是text/html.
实际上不同的资源有对应的Content-Type定义。我们
应当按照请求的实际资源类型在响应时与以正确的告知
客户端该资源类型。


本版本主要改动:
1:首先要修改HttpResponse中发送响应头的操作，不能总是固定
    的发送Content-Type和Content-Length.
    实际上服务端还可以响应其他的响应头给客户端。

2:ClientHandler在得到用户请求的资源后，应当按照该资源的
    类型将对应的Content-Type值设置到HttpResponse中，使其
    响应正确类型。


实现:
1:在HttpResponse中添加一个新的属性:
  Map<String,String> headers
    使用这个属性保存所有要给客户端发送的响应头

2:为该属性提供get，set方法。以便外界可以设置任何向给客户端
     发送的响应头

3:修改sendHeaders方法，将原有的固定发送两个响应头改为
    通过遍历headers这个Map，将所有需要发送的响应头发送给
    客户端。

4:在ClientHandler的分支操作中，按照响应文件的实际类型
    设置HttpResponse要发送的响应头。
    这样最终调用flush方法时就可以按照实际类型响应了。

5:在http包中新建一个类:HttpContext
    在当前类中定义所有有关HTTP协议规定的内容
    这里先添加一个Map类型的静态属性:MIME_MAPPING,用于保存
    所有资源类型与Content-Type对应的值。
    并提供get方法，使得外界可以根据资源类型获取对应的值。
    然后完成初始化操作。
    这样一来ClientHandler在得到客户端请求的资源后，就可以
    使用该资源的类型名获取对应的Content-Type值并设置好进行
    响应了。