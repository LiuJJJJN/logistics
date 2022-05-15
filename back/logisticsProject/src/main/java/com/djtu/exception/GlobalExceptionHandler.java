package com.djtu.exception;

import com.djtu.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*==================== 401 认证 / 授权 引起的错误, 需要重新登录=======================*/

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

    /*==================== 402 不影响程序运行的错误, 在前端会提示=======================*/

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

    @ExceptionHandler(value = BuildingException.class)
    public Result handler(BuildingException e){
        log.error("运行时异常----------------{}", e.getMessage());
        return new Result().setCode(402).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = DormException.class)
    public Result handler(DormException e){
        log.error("运行时异常----------------{}", e.getMessage());
        return new Result().setCode(402).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = UploadException.class)
    public Result handler(UploadException e){
        log.error("运行时异常----------------{}", e.getMessage());
        return new Result().setCode(402).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("运行时异常----------------{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new Result().setCode(402).setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }


    /*==================== 403 不需要处理的错误, 前端不会提示=======================*/

    @ExceptionHandler(value = NothingException.class)
    public Result handler(NothingException e){
        log.error("nothing异常----------------{}", e.getMessage());
        return new Result().setCode(403).setMessage(e.getMessage());
    }

}
