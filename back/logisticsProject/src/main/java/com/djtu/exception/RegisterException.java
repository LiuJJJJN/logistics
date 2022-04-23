package com.djtu.exception;

/**
 * @littleHan
 * 自定义异常注册异常
 */
public class RegisterException extends Exception{
    /**
     * 有参构造方法
     * @param s
     */
    public RegisterException(String s){
        super(s);
    }

    /**
     * 无参构造方法
     */
    public RegisterException(){

    }
}
