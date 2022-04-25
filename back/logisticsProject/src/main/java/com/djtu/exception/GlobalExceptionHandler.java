package com.djtu.exception;

import com.djtu.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    public Result handler(UnauthorizedException e){
        log.error("运行时异常----------------{}" + e.getMessage());
        return new Result().setCode(401).setMessage("无权限访问");
    }

    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public Result handler(ExpiredCredentialsException e) {
        log.error("运行时异常----------------{}", e.getMessage());
        return new Result().setCode(401).setMessage("登录已过期，请重新登录");
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    public Result handler(UnauthenticatedException e) {
        log.error("运行时异常----------------{}", e.getMessage());
        return new Result().setCode(401).setMessage("未登录");
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public Result handler(AuthenticationException e) {
        log.error("运行时异常----------------{}", e.getMessage());
        return new Result().setCode(401).setMessage("未找到此用户");
    }

    @ExceptionHandler(value=RegisterException.class)
    public Result handler(RegisterException e){
        log.error("运行时异常----------------{}",e.getMessage());
        return new Result().setCode(402).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = DictionaryException.class)
    public Result handler(DictionaryException e){
        log.error("运行时异常----------------{}",e.getMessage());
        return new Result().setCode(402).setMessage(e.getMessage());
    }
}
