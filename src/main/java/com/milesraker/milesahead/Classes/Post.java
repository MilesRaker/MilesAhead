package com.milesraker.milesahead.Classes;

public class Post {
    private int id;
    private int posterId;
    private String title;
    private String description;

    public int getId() {
        return id;
    }

    public int getPosterId() {
        return posterId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Post(int id, int posterId, String title, String description){
        this.id=id;
        this.posterId=posterId;
        this.title=title;
        this.description=description;
    }
}
