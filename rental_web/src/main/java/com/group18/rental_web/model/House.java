package com.group18.rental_web.model;

import com.group18.rental_web.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private int capacity;

    private String description;

    @ManyToOne
    private User owner;

    private int rent_per_month;

    // unit: month
    private int rent_term;

    // 房內的租客
    @OneToMany(mappedBy = "resideHouses", cascade = CascadeType.ALL)
    private Set<User> residents;

    public House() {
    }
}
