package com.shrinkster.userservice.service;

import com.shrinkster.userservice.model.User;

import javax.servlet.http.HttpServletRequest;


public interface IUserService {

    User loadByUsername(HttpServletRequest request);

}
