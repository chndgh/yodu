package com.cmdt.yundu.to;

import com.cmdt.yundu.model.Message;
import com.cmdt.yundu.model.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by dghcch on 2017/5/29.
 */
public class NewMessageTO {


    private Message message;

//    @JsonProperty("photos")
    private List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }


}
