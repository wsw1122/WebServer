本版本中我们完成处理请求的环节

当用户在浏览器通过输入一个地址后，服务端根据HttpRequest
得知用户请求的内容并找到该资源，为将来响应客户端做准备。


实现:
1:准备一个webapp需要的资源
首先我们在项目目录下新建一个目录:webapps,使用这个目录保存
所有的网络应用(webapp),每一个网络应用为一个单独的目录，并
且使用目录名作为该网络应用的名字。
一个网络应用大致应当包含:静态资源(网页，图片等),业务逻辑(
java程序)。我们印象中的一个"网站"实际上就是一个网络应用。
而我们写的当前项目WebServer是一个网络容器,可以同时管理多个
在上面运行的网络应用。

2:在webapps下新建一个目录:myweb,作为我们第一个网络应用

3:在myweb下新建第一个静态页面:index.html

4:在ClientHandler中完成第二步操作:处理请求
    这里我们要通过HttpRequest获取请求的抽象路径(url属性的值)
    这个抽象路径是一个相对路径，我们的网络应用都放在了webapps
    目录中，所以我们可以从webapps下开始寻找该资源。