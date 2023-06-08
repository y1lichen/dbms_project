package com.group18.rental_web.payload.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CreateHouseRequest {
    private String title;
    private String address;
    private int capacity;
    private String description;
    private int monthly_fee;
    private int gender;
    private int rent_term;
    private int floor;
    private int prepaid_term;
    private double size;
    private boolean is_suite;
    private List<MultipartFile> images;
    // 用owner email綁定owner
    // 不用email了，從sesssion拿
    // private String ownerEmail;

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public int getPrepaid_term() {
        return prepaid_term;
    }

    public void setPrepaid_term(int prepaid_term) {
        this.prepaid_term = prepaid_term;
    }

    public void setIs_suite(boolean is_suite) {
        this.is_suite = is_suite;
    }

    public boolean getIs_suite() {
        return is_suite;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getMonthly_fee() {
        return monthly_fee;
    }

    public void setMonthly_fee(int monthly_fee) {
        this.monthly_fee = monthly_fee;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    // public String getOwnerEmail() {
    // return ownerEmail;
    // }

    // public void setOwnerEmail(String ownerEmail) {
    // this.ownerEmail = ownerEmail;
    // }

    public void setRent_term(int rent_term) {
        this.rent_term = rent_term;
    }

    public int getRent_term() {
        return rent_term;
    }
}
