本版本完成用户登录操作

按照注册的流程操作完成用户登录

实现:
1:在webapps/myweb目录下新建3个页面
  login.html 登录页面
    在这个页面中定义一个form表单，action指定"./login"
    并且在表单中中定义两个输入框分别用于输入用户名以及
    密码。这两个输入框的name属性分别为:username,password.

  login_success.html  登录成功提示页面
  login_fail.html     登录失败提示页面

2:在servlet包中定义用于处理登录业务
    的类:LoginServlet并定义service方法。

3:在ClientHandler处理请求部分添加一个新的分支，
    若请求路径是"/myweb/login"则实例化LoginServlet
    并调用其service方法处理登录业务。


LoginServlet的登录业务大致流程:
1:首先通过request获取用户在表单上输入的用户名及密码
2:使用RandomAccessFile读取user.dat文件
3:顺序读取每条记录，并比对用户名与密码
    若有比对成功的，则设置response响应登录成功页面
    若用户名正确但密码不正确，或user.dat文件中没有该
    用户，则设置response响应登录失败页面。
