package com.djtu.exception;

import com.djtu.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
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
        return new Result().setCode(401).setMessage("无权限访问，请勿尝试访问非账户权限内的内容");
    }

    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public Result handler(ExpiredCredentialsException e) {
        log.error("运行时异常----------------{}", e.getMessage());
        return new Result().setCode(401).setMessage("登录已过期，请重新登录");
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    public Result handler(UnauthenticatedException e) {
        log.error("运行时异常----------------{}", e.getMessage());
        return new Result().setCode(401).setMessage("未登录，请勿非法访问");
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

    @ExceptionHandler(value = UserManagerException.class)
    public Result handler(UserManagerException e){
        log.error("运行时异常----------------{}",e.getMessage());
        return new Result().setCode(402).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = FeedbackException.class)
    public Result handler(FeedbackException e){
        log.error("运行时异常----------------{}",e.getMessage());
        return new Result().setCode(402).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = ReplyException.class)
    public Result handler(ReplyException e){
        log.error("运行时异常----------------{}",e.getMessage());
        return new Result().setCode(402).setMessage(e.getMessage());
    }

//    @ExceptionHandler(value = NullPointerException.class)
//    public Result handler(NullPointerException e){
//        log.error("运行时异常----------------{}",e.getMessage());
//        return new Result().setCode(401).setMessage("服务端异常, 请重新登陆");
//    }

}
