package com.yang.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.vo.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@Component
public class MySuccessHandler implements AuthenticationSuccessHandler {
    /*
        参数：
            request：请求对象
            response：应答对象
            authentication：spring security框架验证用户信息成功后的封装类
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //登录用户信息验证成功后执行的方法
        response.setContentType("text/json;charset=utf-8");

        Result result = new Result();
        result.setCode(0);
        result.setError(1000);
        result.setMsg("登陆成功！");
        OutputStream out = response.getOutputStream();
        ObjectMapper om = new ObjectMapper();
        om.writeValue(out,result);
        out.flush();
        out.close();
    }
}
