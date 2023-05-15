package com.group18.rental_web.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private Date postedTime;

    @OneToOne
    // 發文者
    private User owner;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private Set<PostImage> postImages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPostedTime(Date postedTime) {
        this.postedTime = postedTime;
    }

    public Date getPostedTime() {
        return postedTime;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void setPostImages(Set<PostImage> postImages) {
        this.postImages = postImages;
    }

    public Set<PostImage> getPostImages() {
        return postImages;
    }
}
