package com.m101j.hw77;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

@Entity("albums")
public class Album {

    public static final String FIELD_IMAGES = "images";

    @Id
    private Integer id;

    private List<Image> images;

    public Integer getId() {
        return id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
