package com.group18.rental_web.entities.house;

import com.group18.rental_web.entities.user.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private int capacity;

    private String description;

    private User owner;

    private int rent_per_month;

    // unit: month
    private int rent_term;

    // 房內的租客
    @OneToMany
    private Set<User> residents;

    public House() {
    }
}
