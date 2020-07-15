package com.muye.kl.controller;

import com.muye.kl.exception.MyException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/testException")
    public String testException() throws Exception{
        throw new MissingServletRequestParameterException("name","String");
    }

    @GetMapping("testMyException")
    public String testMyException() throws MyException {
        throw new MyException("i am a myException");
    }
}