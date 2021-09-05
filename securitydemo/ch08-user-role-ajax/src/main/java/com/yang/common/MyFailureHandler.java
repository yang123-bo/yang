package com.yang.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.vo.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@Component
public class MyFailureHandler implements AuthenticationFailureHandler {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        //当框架验证用户信息失败时执行的方法
        response.setContentType("text/json;charset=utf-8");

        if (result == null){
            Result localResult = new Result();
            localResult.setCode(1);
            localResult.setError(1001);
            localResult.setMsg("登录失败！");
            result = localResult;
        }


        OutputStream out = response.getOutputStream();
        ObjectMapper om = new ObjectMapper();
        om.writeValue(out,result);
        out.flush();
        out.close();
    }
}
