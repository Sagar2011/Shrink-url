package com.shrinkster.urlservice.service;

import com.google.common.hash.Hashing;
import com.shrinkster.urlservice.model.Url;
import com.shrinkster.urlservice.repository.UrlRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Value("${redirect-link}")
    String redirectLink;

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

    public String postUrl(Url url){
        System.out.println("in service id");
        String id = Hashing.murmur3_32().hashString(url.getUrlLink(), Charset.defaultCharset()).toString();
        System.out.println("id"+id);
        url.setTinyUrl(id);
        url.setUrlId(UUID.randomUUID());
        url.setGenerateDate(new Date());
        urlRepository.save(url);
        String tinyUrl = redirectLink + url.getTinyUrl();
        return tinyUrl;
    }

    public List<Url> getAllUrl(){
        return urlRepository.findAll();
    }

    public List<Url> getUserUrl(String user){
        return urlRepository.findByUserId(user);
    }

    public Url getOriginalLink(String id) { return urlRepository.findByTinyUrl(id); }
}
