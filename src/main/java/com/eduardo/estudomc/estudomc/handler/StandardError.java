package com.eduardo.estudomc.estudomc.handler;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private String msg;
    private Long TimeStamp;

    public StandardError(){

    }

    public StandardError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        TimeStamp = timeStamp;
    }
}
