package com.eduardo.estudomc.estudomc.exceptions;

import com.eduardo.estudomc.estudomc.handler.StandardError;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(){

    }

    public ValidationError(Integer status, String msg, Long timeStamp, List<FieldMessage> list) {
        super(status, msg, timeStamp);
        this.errors = list;
    }

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public void addError(String fieldName, String messagem){
        errors.add(new FieldMessage(fieldName, messagem));
    }
}
