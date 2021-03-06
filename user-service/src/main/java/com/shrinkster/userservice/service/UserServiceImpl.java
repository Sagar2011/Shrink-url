package com.shrinkster.userservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.shrinkster.userservice.exception.DatabaseEmptyException;
import com.shrinkster.userservice.model.User;
import com.shrinkster.userservice.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserRepository userRepo;

    static String SIGNINGKEY;

    @Value("${SIGNING_KEY}")
    public void setSigningkey(String signingkey) {
        SIGNINGKEY = signingkey;
    }

    // For getting the user profile using username from the database
    @Override
    public User loadByUsername(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("Shrink_bun")) {
                    String user;
                    try {
                        user = Jwts.parser().setSigningKey(SIGNINGKEY).parseClaimsJws(cookie.getValue()).getBody()
                                .get("em", String.class);
                    } catch (ExpiredJwtException exception) {
                        logger.error("In loadByUsername method" + LocalDateTime.now() + " " + exception.getMessage());
                        return null;
                    }
                    if (user != null) {
                        return this.userRepo.findByEmail(user);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public User findByUserEmail(String email) {
        return userRepo.findByEmail(email);
    }

    // For getting all the client profile data from the database
    @Override
    public List<User> findAllUsers() throws DatabaseEmptyException {
        if (userRepo.findAll().size() == 0) {
            throw new DatabaseEmptyException("Database is Empty");
        } else {
            return userRepo.findAll();
        }
    }

    @Override
    public List<User> getUsers(){
        List<User> list = new ArrayList<User>();
        list = userRepo.findAll();
        return list;
    }

}
