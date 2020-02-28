package com.shrinkster.urlservice.model;

import org.springframework.stereotype.Component;

@Component
public class TinyUrl {

    int urlCount;

    public TinyUrl(int urlCount) {
        this.urlCount = urlCount;
    }

    public TinyUrl() {
    }

    public int getUrlCount() {
        return urlCount;
    }

    public void setUrlCount(int urlCount) {
        this.urlCount = urlCount;
    }
}
