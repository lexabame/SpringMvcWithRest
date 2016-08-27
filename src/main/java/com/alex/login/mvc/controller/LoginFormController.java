package com.alex.login.mvc.controller;

import com.alex.login.mvc.data.LoginMvcModel;
import com.alex.login.mvc.data.UserLogged;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by alejandro on 8/21/16.
 */

@Controller
@RequestMapping(value = "/login.htm")
public class LoginFormController {

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(LoginMvcModel loginMvcModel, BindingResult result) {

        String urlString = "http://localhost:8080/back/rest/login/authenticate";

        RestTemplate restTemplate = new RestTemplate();

        // create request body
        JSONObject request = new JSONObject();
        request.put("username", loginMvcModel.getUsername());
        request.put("password", loginMvcModel.getPassword());
        request.put("firstName", "");
        request.put("lastName", "");
        request.put("role", "");

// set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

// send request and parse result
        ResponseEntity<String> loginResponse = restTemplate
                .exchange(urlString, HttpMethod.POST, entity, String.class);

        JSONObject userJson = new JSONObject();

        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            userJson = new JSONObject(loginResponse.getBody());
        } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            // nono... bad credentials
        }

        UserLogged userLogged = new UserLogged();

       if(userJson != null){
            userLogged.setUsername(userJson.get("username").toString());
            userLogged.setPassword(userJson.get("password").toString());
            userLogged.setFirstName(userJson.get("firstName").toString());
            userLogged.setLastName(userJson.get("lastName").toString());
            userLogged.setRole(userJson.get("role").toString());
        }

        ModelAndView model = new ModelAndView("private/privatearea", "user", userLogged);

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