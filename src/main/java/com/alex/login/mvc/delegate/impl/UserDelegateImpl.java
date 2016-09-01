package com.alex.login.mvc.delegate.impl;

import com.alex.login.mvc.data.LoginMvcModel;
import com.alex.login.mvc.data.User;
import com.alex.login.mvc.delegate.api.UserDelegate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alejandro on 9/1/16.
 */

@Service
public class UserDelegateImpl implements UserDelegate {

    public static final String AUTHENTICATE_URL = "http://localhost:8080/back/rest/login/authenticate";
    public static final String GET_USERS_URL = "http://localhost:8080/back/rest/users/all";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User authenticateRest(final LoginMvcModel loginMvcModel) {

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
                .exchange(AUTHENTICATE_URL, HttpMethod.POST, entity, String.class);

        JSONObject userJson = new JSONObject();

        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            userJson = new JSONObject(loginResponse.getBody());
        } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            // nono... bad credentials
        }

        User user = new User();

        if(userJson != null){
            user.setUsername(userJson.get("username").toString());
            user.setPassword(userJson.get("password").toString());
            user.setFirstName(userJson.get("firstName").toString());
            user.setLastName(userJson.get("lastName").toString());
            user.setRole(userJson.get("role").toString());
        }

        return user;

    }

    @Override
    public List<User> getUsersRest() {

        RestTemplate restTemplate = new RestTemplate();

        // create request body

// set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        //ResponseEntity<Object[]> response = restTemplate.getForEntity(
          //      GET_USERS_URL, Object[].class);

        //List<Object> users= Arrays.asList(response.getBody());

        User user = restTemplate.getForObject(GET_USERS_URL, User.class);

        return null;
    }
}
