ch06-jdbcUserDetailsService：使用数据库，获取认证的用户信息

底层使用的spring中的jdbcTemplate访问数据库，需要加入jdbc依赖，数据库mysql依赖

实现步骤：
1.新建maven项目
2.maven的坐标
    1）spring-boot
    2）spring-security
    3）spring-web
    4）spring-jdbc
    5）mysql
3.创建应用的配置类，创建JdbcUserDetailsService对象
    获取数据库中user表的数据

4.创建一个security的配置类，自定义安全配置信息。指定JdbcUserDetailsService类

5.修改application.properties文件
    连接数据库，配置数据源
6.测试