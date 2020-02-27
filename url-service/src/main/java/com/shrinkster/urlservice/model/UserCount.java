package com.shrinkster.urlservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "usercount")
public class UserCount {

    @Id
    private UUID userCountId;
    private String userId;
    private UUID urlId;

    public UserCount() {
    }

    public UUID getUserCountId() {
        return userCountId;
    }

    public void setUserCountId(UUID userCountId) {
        this.userCountId = userCountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UUID getUrlId() {
        return urlId;
    }

    public void setUrlId(UUID urlId) {
        this.urlId = urlId;
    }
}
