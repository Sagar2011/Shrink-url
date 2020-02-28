package com.shrinkster.urlservice.service;

import com.shrinkster.urlservice.model.TinyUrl;
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

    @Autowired
    private TinyUrl tinyUrl;

    private static Date date = new Date();

    @Scheduled(fixedRate = 5000)
    public void fireGreeting() {
        List<Url> links = urlService.getAllUrl();
        int data=0;
        for (Url list:links) {
            if(list.getGenerateDate().getTime() < new  Date().getTime() && list.getGenerateDate().getTime() > date.getTime()){
            Long seconds = (list.getGenerateDate().getTime()-new Date().getTime())/1000;
            System.out.println("sec"+seconds);
            if(!seconds.equals(0L)){
                data++;
                System.out.println("data>>>>>"+data);
            }
        }
        }
        date = new Date();
        tinyUrl.setUrlCount(data);
        this.template.convertAndSend("/topic/greetings", tinyUrl);
    }
}
