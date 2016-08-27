package com.alex.login.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by alejandro on 8/21/16.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/welcome.htm")
    public ModelAndView mainPage(HttpServletRequest request)
            throws ServletException, IOException {

        ModelAndView model = new ModelAndView("mainPage");

        return model;

    }

    @RequestMapping(value = "/login.htm")
    public ModelAndView login(HttpServletRequest request)
            throws ServletException, IOException {

        ModelAndView model = new ModelAndView("login");

        return model;

    }


}
