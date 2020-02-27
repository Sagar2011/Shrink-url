package com.shrinkster.urlservice.service;

import com.shrinkster.urlservice.model.Url;
import com.shrinkster.urlservice.model.UserCount;
import com.shrinkster.urlservice.repository.UserCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UrlCountService {

    @Autowired
    private UserCountRepository userCountRepository;

    @Autowired
    private UrlService urlService;

    public void saveToCount(String user, UUID linkid){
            UserCount userCount = new UserCount() ;
            userCount.setUserCountId(UUID.randomUUID());
            userCount.setUserId(user);
            userCount.setUrlId(linkid);
            userCountRepository.save(userCount);
    }

    public List<UserCount> getStatus(String userId){
        return userCountRepository.findByUserId(userId);
    }

    public void deleteLinks(String user){
        List<Url> links = urlService.getUserUrl(user);
        List<UserCount> urls = userCountRepository.findByUserId(user);
        for (Url list: links) {
            for (UserCount ob: urls) {
                if (!(list.getUrlId().equals(ob.getUrlId()))){
                    userCountRepository.deleteById(ob.getUserCountId().toString());
                }
            }
        }
    }
}
