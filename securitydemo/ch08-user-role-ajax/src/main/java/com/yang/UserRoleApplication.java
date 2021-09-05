package com.yang;

import com.yang.entity.SysUser;
import com.yang.mapper.SysUserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MapperScan(value = "com.yang.mapper")
@SpringBootApplication
public class UserRoleApplication {

    @Autowired
    SysUserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(UserRoleApplication.class,args);
    }

    //@PostConstruct
    public void JdbcInit(){

        Date curDate = new Date();
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        List<GrantedAuthority> list = new ArrayList<>();
        //以ROLE_开头
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+"READ");
        list.add(authority);


        SysUser user = new SysUser(
                "lisi",encoder.encode("456"),"李四",true,true,true,true,curDate,curDate,list
        );
        userMapper.insertSysUser(user);


        List<GrantedAuthority> list2 = new ArrayList<>();
        GrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_"+"ADMIN");
        GrantedAuthority authority3 = new SimpleGrantedAuthority("ROLE_"+"USER");
        list.add(authority2);
        list.add(authority3);

        SysUser user2 = new SysUser(
                "admin",encoder.encode("admin"),"管理员",true,true,true,true,curDate,curDate,list2
        );
        userMapper.insertSysUser(user2);
    }
}
