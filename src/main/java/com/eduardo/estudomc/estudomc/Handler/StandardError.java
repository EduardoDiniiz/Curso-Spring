package com.eduardo.estudomc.estudomc.Handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private String msg;
    private Long TimeStamp;

}
