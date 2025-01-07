package com.carcaddy.main.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEntityException.class)
    public String handleInvalidEntityException(InvalidEntityException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "create-maintenance"; // Return a generic error page
    }

//    @ExceptionHandler(Exception.class)
//    public String handleGenericException(Exception ex, Model model) {
//        model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
//        return "error"; // Return a generic error page
//    }
}
