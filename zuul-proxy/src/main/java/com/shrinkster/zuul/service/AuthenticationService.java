package com.shrinkster.zuul.service;


import java.util.Collections;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Service
public class AuthenticationService {

    static String SIGNINGKEY;

    @Value("${SIGNING_KEY}")
    public void setSigningkey(String signingkey) {
        SIGNINGKEY = signingkey;
    }

    // Authentication service for validating the jwt token
    public static Authentication getAuthentication(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("Shrink_bun")) {
                    String user;
                    try {
                        Claims claims = Jwts.parser().setSigningKey(SIGNINGKEY).parseClaimsJws(cookie.getValue()).getBody();
                        user = claims.get("em", String.class);
                        String roles = claims.get("roles", String.class);
                        List<GrantedAuthority> grantedAuths =
                                AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
                        if (user != null) {
                            return new UsernamePasswordAuthenticationToken(user, null, grantedAuths);
                        }
                    } catch (ExpiredJwtException exception) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

}
