package com.group18.rental_web.entities.house;

import com.group18.rental_web.entities.user.User;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

public class House {
    @Id
    private int id;

    private String address;

    private int capacity;

    private String description;

    private User owner;

    private int rent_per_month;

    // unit: month
    private int rent_term;

    @OneToMany
    private Set<User> residents;

    public House() {
    }
}
