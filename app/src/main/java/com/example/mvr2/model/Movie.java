package com.example.mvr2.model;

import java.io.Serializable;

/**
 * Created by kununya1996 on 11/5/2016.
 */

public class Movie implements Serializable {

    private String id;
    private String name;
    private String picture;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
}