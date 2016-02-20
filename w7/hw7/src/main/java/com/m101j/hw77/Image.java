package com.m101j.hw77;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

@Entity("images")
public class Image {

    public static final String FIELD_TAGS = "tags";

    @Id
    private Integer id;

    private Integer height;

    private Integer width;

    private List<String> tags;

    public Integer getId() {
        return id;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
