package com.group18.rental_web.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class House {

    public House() {
    }

    public House(String title, String address, int capacity, String description, int floor,
            User owner, int rentPerMonth, int rentTerm, int gender, int prePaidTerm, double size, boolean isSuite) {
        this.title = title;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
        this.floor = floor;
        this.owner = owner;
        this.rentPerMonth = rentPerMonth;
        this.rentTerm = rentTerm;
        this.gender = gender;
        this.prePaidTerm = prePaidTerm;
        this.size = size;
        this.isSuite = isSuite;
        this.clickTimes = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String address;

    private int capacity;

    private String description;

    // 坪數
    private double size;

    // 是不是套房
    private boolean isSuite;

    // 0-> male, 1->female, 2->both
    private int gender;

    @ManyToOne
    private User owner;

    private int rentPerMonth;

    // unit: month
    private int rentTerm;

    private int floor;

    private int prePaidTerm;

    private int clickTimes;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

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

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<HouseImage> houseImages;

    public void setHouseImages(Set<HouseImage> houseImages) {
        if (this.houseImages == null) {
            this.houseImages = houseImages;
        } else if (this.houseImages != houseImages) {
            this.houseImages.clear();
            this.houseImages.addAll(houseImages);
        }
    }

    public Set<HouseImage> getHouseImages() {
        return houseImages;
    }

    // 房內的租客
    // @OneToMany(mappedBy = "resideHouses", cascade = CascadeType.ALL)
    // private Set<User> residents;

    public void setSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setIsSuite(boolean isSuite) {
        this.isSuite = isSuite;
    }

    public boolean getIsSuite() {
        return isSuite;
    }

    public int getPrePaidTerm() {
        return prePaidTerm;
    }

    public void setPrePaidTerm(int prePaidTerm) {
        this.prePaidTerm = prePaidTerm;
    }

    public int getClickTimes() {
        return clickTimes;
    }

    public void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }

}
