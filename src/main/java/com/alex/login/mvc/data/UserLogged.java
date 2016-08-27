package com.alex.login.mvc.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by alejandro on 8/21/16.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogged {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

}
