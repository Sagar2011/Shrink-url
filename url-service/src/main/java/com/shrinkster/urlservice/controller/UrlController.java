package com.shrinkster.urlservice.controller;

import com.shrinkster.urlservice.model.Url;
import com.shrinkster.urlservice.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/generate")
    public ResponseEntity<?> postUrl(@RequestBody Url url, HttpServletRequest httpServletRequest){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url.getUrlLink());
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = this.restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET, request,
                String.class);
        System.out.println(response.getStatusCode());
        String user = urlService.loadByUsername(httpServletRequest);
        if(response.getStatusCode().compareTo(HttpStatus.OK)==200){
            urlService.postUrl(url);
            return new ResponseEntity<>("url is successfullly saved", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("url is not valid", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
