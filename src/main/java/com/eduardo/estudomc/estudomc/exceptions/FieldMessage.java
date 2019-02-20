package com.eduardo.estudomc.estudomc.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String messagem;

    public FieldMessage(){

    }

    public FieldMessage(String fieldName, String messagem){
        this.fieldName = fieldName;
        this.messagem = messagem;
    }

}
