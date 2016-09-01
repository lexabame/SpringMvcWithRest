package com.alex.login.mvc.delegate.api;

import com.alex.login.mvc.data.LoginMvcModel;
import com.alex.login.mvc.data.User;

import java.util.List;

/**
 * Created by alejandro on 9/1/16.
 */
public interface UserDelegate {

    User authenticateRest(LoginMvcModel loginMvcModel);

    List<User> getUsersRest();

}
