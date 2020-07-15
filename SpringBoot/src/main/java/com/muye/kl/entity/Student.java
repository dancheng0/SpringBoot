package com.muye.kl.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : gwh
 * @Desc:
 * @date : 2020-07-03 15:55
 **/
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    private Long id;
    private String userName;
    private String passWord;
}
