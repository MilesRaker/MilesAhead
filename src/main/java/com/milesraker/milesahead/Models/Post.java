package com.milesraker.milesahead.Models;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private int posterId;

    @Column(nullable=false, length=100, columnDefinition = "TEXT")
    private String title;

    @Column(length=512, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {}

    public Post(int posterId, String title, String description, User user){
        this.posterId = posterId;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public long getId() { return id; }
    public String getIdString() { return Long.toString(id); }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPosterId() {
        return posterId;
    }

    public User getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
