package com.cmdt.yundu.to;

import com.cmdt.yundu.model.Message;
import com.cmdt.yundu.model.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by dghcch on 2017/5/29.
 */
public class NewMessageTO {


    private Message message;
//    @JsonProperty("photos")
    private List<Photo> photos;

    private List<MultipartFile> images;

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

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}
