package com.academy.nhnmartcs.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidationFailedException extends Throwable {
    public ValidationFailedException(BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            System.out.println("Please check the input: " + error);
        }
    }
}
