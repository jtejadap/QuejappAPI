package com.example.quejapp.controller;

import com.example.quejapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.security.Principal;

@ControllerAdvice
public class ExceptionsController {
    public static final String DEFAULT_ERROR_VIEW = "ErrorInternal";
    private final UserService servicio;

    public ExceptionsController(UserService servicio) {
        this.servicio = servicio;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(
            HttpServletRequest req,
            Exception e,
            Principal principal
    ) {
        ModelAndView view = new ModelAndView();

        view.addObject("exception", e);
        view.addObject("url", req.getRequestURL());
        view.addObject("link", servicio.returnHomeLinkByRole(principal));

        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ModelAndView noResourceFoundErrorHandler(
            HttpServletRequest req,
            Exception e,
            Principal principal
    ) {
        ModelAndView view = new ModelAndView();

        view.addObject("exception", e.getMessage());
        view.addObject("url", req.getRequestURL());
        view.addObject("link", servicio.returnHomeLinkByRole(principal));

        view.setViewName("ErrorResourceNotFound");
        return view;
    }


}
