ch05-memoryUserDetails:使用InMemoryUserDetailsManager管理内存中的用户信息

实现步骤：
1.新建项目
2.加入依赖
    boot、security、web
3.创建配置类
    1）创建密码的处理类
    2）用InMemoryUserDetailsManager创建用户
4.创建类继承WebSecurityConfigurerAdapter
    自定义安全配置
5.测试
