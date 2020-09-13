package ru.tsystems.school.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
@Log4j
public class ErrorController {

    private static final String EXCEPTION = "Exception";

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView cantCreate(RuntimeException e) {
        log.error(EXCEPTION, e);
        ModelAndView modelAndView = new ModelAndView("errors/cant_do_it");
        String message = e.getMessage();
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
