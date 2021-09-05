ch07-user-role:使用用户和角色，认证用户

实现步骤：
1.新建maven项目
2.maven的坐标
    1）spring-boot
    2）spring-security
    3）spring-web
    4）spring-mybatis
    5）mysql
3.修改application.properties文件
    连接数据库，配置数据源

4.创建自己的user类，代替UserDetails

5.创建自定义的UserDetailsService实现类
    在重写方法中，查询数据库获取用户信息，获取角色数据
    构建UserDetails实现类对象

6.创建了继承WebSecurityConfigurerAdapter
    自定义安全的配置

7.自定义登录
    1）传统的表单登录form
    2）ajax登录

8.创建controller