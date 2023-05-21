package com.group18.rental_web.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class House {

    public House() {
    }

    public House(String title, String address, int capacity, String description,
                 String userEmail, int rentPerMonth, int rentTerm, int gender) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String address;

    private int capacity;

    private String description;

    // 0-> male, 1->female, 2->both
    private int gender;

    @ManyToOne
    private User owner;

    private int rentPerMonth;

    // unit: month
    private int rentTerm;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRentPerMonth() {
        return rentPerMonth;
    }

    public void setRentPerMonth(int rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
    }

    public int getRentTerm() {
        return rentTerm;
    }

    public void setRentTerm(int rentTerm) {
        this.rentTerm = rentTerm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    private int floor;

    // 房內的租客
    @OneToMany(mappedBy = "resideHouses", cascade = CascadeType.ALL)
    private Set<User> residents;


}
