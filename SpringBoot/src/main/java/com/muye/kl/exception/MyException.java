package com.muye.kl.exception;

import lombok.Data;

/**
 * 自定义异常类
 */
@Data
public class MyException extends RuntimeException {
    private long code;
    private String msg;

    public MyException(Long code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MyException(String msg){
        super(msg);
        this.msg = msg;
    }
}

