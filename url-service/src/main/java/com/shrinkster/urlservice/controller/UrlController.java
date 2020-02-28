package com.shrinkster.urlservice.controller;

import com.shrinkster.urlservice.model.TinyUrl;
import com.shrinkster.urlservice.model.Url;
import com.shrinkster.urlservice.model.UserCount;
import com.shrinkster.urlservice.service.ScheduledTasks;
import com.shrinkster.urlservice.service.UrlCountService;
import com.shrinkster.urlservice.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UrlController {
    static int count = 0;
    @Autowired
    private UrlService urlService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UrlCountService urlCountService;

    @Autowired
    private ScheduledTasks scheduledTasks;

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
        url.setUserId(user);
        if(response.getStatusCode().value()==200){
            String link = urlService.postUrl(url);
            Set<String> linkSet = new HashSet<>();
            linkSet.add(link);
//            scheduledTasks.fireGreeting();
            return new ResponseEntity<>(linkSet, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("url is not valid", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("/findAllLinks")
    public List<Url> getlink() {
        return urlService.getAllUrl();
    }


    @GetMapping("/link/{id}")
    public ResponseEntity<?> getUrl(@PathVariable String id, HttpServletResponse httpServletResponse) {
        Url url = urlService.getOriginalLink(id);
        try {
            if(url != null){
            httpServletResponse.sendRedirect(url.getUrlLink());
            return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> getNotification(HttpServletRequest request){
        String user = urlService.loadByUsername(request);
        List<UserCount> countLinks = urlCountService.getStatus(user);
        List<Url> links = urlService.getUserUrl(user);
        if(countLinks.size() != links.size()){
            Set<Integer> linkSet = new HashSet<>();
            linkSet.add(countLinks.size()-links.size());
            urlCountService.deleteLinks(user);
            return new ResponseEntity<>(linkSet, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/urls")
    public ResponseEntity<?> getUrls(HttpServletRequest request){
        try{ String user = urlService.loadByUsername(request);
            List<Url> links = urlService.getUserUrl(user);
            return new ResponseEntity<>(links, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getUrl(@RequestParam String user){
        try{
//        String user = urlService.loadByUsername(request);
            List<Url> links = urlService.getUserUrl(user);
            return new ResponseEntity<>(links, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/urlsCount/{userId}")
    public List<UserCount> getUrlsCount(@PathVariable String userId, HttpServletResponse httpServletResponse){
        return urlCountService.getUrlCount (userId);
    }
    //web socket for the graph purpose
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ResponseEntity<?> greeting(TinyUrl tinyUrl) throws Exception {
        return new ResponseEntity<TinyUrl>(tinyUrl,HttpStatus.OK);
    }
}
