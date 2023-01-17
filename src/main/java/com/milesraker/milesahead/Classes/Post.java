package com.milesraker.milesahead.Classes;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private int posterId;

    @Column(nullable=false, length=100)
    private String title;

    @Column(length=512)
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

}
