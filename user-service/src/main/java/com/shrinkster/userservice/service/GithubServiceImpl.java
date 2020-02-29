package com.shrinkster.userservice.service;

import com.shrinkster.userservice.constants.Roles;
import com.shrinkster.userservice.model.User;
import com.shrinkster.userservice.repository.IUserRepository;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GithubServiceImpl {

    @Value("${github.userInfoUrl}")
    private String baseUrl;

    @Value("${github.clientId}")
    String clientId;

    @Value("${github.clientSecret}")
    String clientSecret;

    @Value("${github.accessTokenUrl}")
    private String accesToken;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    User user;

    @Autowired
    IGoogleService iGoogleService;


    @Autowired
    private IUserRepository userRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public String getOAuthAccessToken(String code){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("code", code);
        HttpEntity<Map> request = new HttpEntity<>(params, headers);
        ResponseEntity<JSONObject> result  = this.restTemplate.exchange(accesToken,HttpMethod.POST, request, JSONObject.class);
        System.out.println("result::::"+result.getBody());
        return result.getBody().get("access_token").toString();
    }

    public User getUserInfo(String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + accessToken);
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        ResponseEntity<JSONObject> result = this.restTemplate.exchange(baseUrl, HttpMethod.GET, entity, JSONObject.class);
        JSONObject userDetails = result.getBody();
        user.setAvatarURL((String) userDetails.get("avatar_url"));
        user.setEmail((String) userDetails.get("html_url"));
        user.setName((String) userDetails.get("login"));
        user.setLoggedIn(true);
            user.setRoles(Roles.USER);
            iGoogleService.addUserData(user);
        return user;
    }
}
