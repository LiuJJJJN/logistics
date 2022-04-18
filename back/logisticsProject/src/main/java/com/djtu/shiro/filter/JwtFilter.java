package com.djtu.shiro.filter;

import com.djtu.response.Result;
import com.djtu.token.JwtToken;
import com.djtu.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter extends AuthenticatingFilter {

    /**
     * 创建 token 的方法
     * @param servletRequest req
     * @param servletResponse resp
     * @return 创建好的 token
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse)
            throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //由前端在请求头中 Authorization 添加的 jwt
        String jwt = request.getHeader("Authorization");
        if(StringUtils.isEmpty(jwt)) {
            return null;
        }
        return new JwtToken(jwt);
    }

    /**
     * 拦截的主要方法
     * @param servletRequest req
     * @param servletResponse resp
     * @return 是否通过拦截
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse)
            throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //如果没有这个头, 就是第一次请求, 所以就通过
        String jwt = request.getHeader("Authorization");
        if(StringUtils.isEmpty(jwt)) {
            return true;
        } else {
            // 校验jwt
            Claims claim = JwtUtil.parseJWT(jwt);
            if(claim == null || JwtUtil.isTokenExpired(claim.getExpiration())) {
                HttpServletResponse response = (HttpServletResponse)servletResponse;
                response.setContentType("application/plain;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(new Result().setCode(401).setMessage("身份已过期，请重新登录")));
                return false;
//                throw new ExpiredCredentialsException("token已失效，请重新登录");
            }

            // 执行登录
            return executeLogin(servletRequest, servletResponse);
        }
    }

    /**
     * 登录失败时进行的操作
     * @param token token
     * @param e exception
     * @param request req
     * @param response resp
     * @return bool
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Throwable throwable = e.getCause() == null ? e : e.getCause();

        Result result = new Result().setMessage(e.getMessage());
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        try {
            httpServletResponse.getWriter().print(json);
        } catch (IOException ioException) {

        }
        return false;
    }

}
