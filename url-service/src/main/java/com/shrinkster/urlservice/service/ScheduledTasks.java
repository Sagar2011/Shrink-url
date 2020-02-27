package com.shrinkster.urlservice.service;

import com.shrinkster.urlservice.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private UrlService urlService;

    @Scheduled(fixedRate = 3000)
    public void fireGreeting() {
        List<Url> links = urlService.getAllUrl();
        int data= 0;
        for (Url list:links) {
            long seconds = (list.getGenerateDate().getTime()-new Date().getTime())/1000;
            if(seconds > 0){
                data++;
            }
        }
        this.template.convertAndSend("/topic/greetings", data);
    }
}
