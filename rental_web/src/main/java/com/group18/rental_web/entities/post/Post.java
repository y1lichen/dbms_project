package com.group18.rental_web.entities.post;


import com.group18.rental_web.entities.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private Date postedTime;

    // 發文者
    private User owner;

    @OneToMany(orphanRemoval = true)
    private Set<PostImage> postImages;

}
