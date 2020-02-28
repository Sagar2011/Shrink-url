package com.shrinkster.userservice.service;

import com.mongodb.DuplicateKeyException;
import com.shrinkster.userservice.exception.UserNotFoundException;
import com.shrinkster.userservice.model.User;

public interface IGoogleService {

    String getAccessToken(String code) throws UserNotFoundException;

    User getUserProfile(String accessToken) throws UserNotFoundException;

    void addUserData(User user) throws DuplicateKeyException;

}
