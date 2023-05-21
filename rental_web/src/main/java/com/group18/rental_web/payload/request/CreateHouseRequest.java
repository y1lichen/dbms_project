package com.group18.rental_web.payload.request;

public class CreateHouseRequest {
    private String title;
    private String address;
    private int capacity;
    private String description;
    private String monthly_fee;
    private String gender;
    private int floor;
    // 用owner email綁定owner
    private String ownerEmail;

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

    public String getMonthly_fee() {
        return monthly_fee;
    }

    public void setMonthly_fee(String monthly_fee) {
        this.monthly_fee = monthly_fee;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
