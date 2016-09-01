package com.alex.login.mvc.controller;

import com.alex.login.mvc.data.LoginMvcModel;
import com.alex.login.mvc.data.User;
import com.alex.login.mvc.delegate.api.UserDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by alejandro on 8/21/16.
 */

@Controller
@RequestMapping(value = "/login.htm")
public class LoginFormController {

    @Autowired
    private UserDelegate userDelegate;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(LoginMvcModel loginMvcModel, BindingResult result) {

        User user = userDelegate.authenticateRest(loginMvcModel);
        List<User> users = userDelegate.getUsersRest();

        ModelAndView model = new ModelAndView("private/privatearea", "user", user);

        return model;

    }

    @RequestMapping(method = RequestMethod.GET)
    protected LoginMvcModel formBackingObject(HttpServletRequest request) throws ServletException {
        LoginMvcModel loginMvcModel = new LoginMvcModel();
        loginMvcModel.setUsername("");
        loginMvcModel.setPassword("");
        return loginMvcModel;
    }

}