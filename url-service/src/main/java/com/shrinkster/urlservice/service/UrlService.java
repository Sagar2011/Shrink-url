package com.shrinkster.urlservice.service;

import com.shrinkster.urlservice.model.Url;
import com.shrinkster.urlservice.repository.UrlRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    // For getting the user profile using username from the database
    public String loadByUsername(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("Shrink_bun")) {
                    String user;
                    try {
                        user = Jwts.parser().setSigningKey("$hr!nk$ter").parseClaimsJws(cookie.getValue()).getBody()
                                .get("em", String.class);
                    } catch (ExpiredJwtException exception) {
                        System.out.println("In loadByUsername method" + LocalDateTime.now() + " " + exception.getMessage());
                        return null;
                    }
                    if (user != null) {
                        return user;
                    }
                }
            }
        }
        return null;
    }

    public void postUrl(Url url){
        url.setUrlId(UUID.randomUUID());
        url.setGenerateDate(new Date());
        urlRepository.save(url);
    }

    public List<Url> getAllUrl(){
        return urlRepository.findAll();
    }

    public List<Url> getUserUrl(String user){
        return urlRepository.findByUserId(user);
    }
}
