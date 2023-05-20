package com.group18.rental_web.payload.request;

public class CreateHouseRequest {

    private String address;

    private int capacity;

    private String description;

    private int rentPerMonth;

    // unit: month
    private int rentTerm;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRentPerMonth(int rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
    }

    public int getRentPerMonth() {
        return rentPerMonth;
    }

    public void setRentTerm(int rentTerm) {
        this.rentTerm = rentTerm;
    }

    public int getRentTerm() {
        return rentTerm;
    }
}
