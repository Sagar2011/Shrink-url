package com.shrinkster.urlservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "url")
public class Url {

    @Id
    private UUID urlId;
    private String urlLink;
    private String tinyUrl;
    @Indexed(expireAfterSeconds = 172800)
    private Date generateDate;
    private String userId;

    public Url(){

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

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public Date getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }
}
