package com.shrinkster.userservice.service;


import com.shrinkster.userservice.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface IUserService {

    User loadByUsername(HttpServletRequest request);
    List<User> getUsers();
}
