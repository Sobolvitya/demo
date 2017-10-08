package com.demo.service;

import com.demo.BaseDomain.UserBase;
import com.demo.domains.User;

/**
 * Created by Vitya on 20.11.2016.
 */
public interface SecurityService {

    void autologin(String email, String password);

    boolean loginUser(String email, String password);

    UserBase getAuthenticatedUser();

}