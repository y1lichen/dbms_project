package com.group18.rental_web.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String hashedPassword;

    private String name;

    // 0: male, 1: female, 2: other
    private int gender;

    private String phone;

    private String token;

    private boolean isForeign;

    private String nation;

    private String selfDescription;

    // 房東擁有的房子
    @OneToMany
    private Set<House> ownedHouse;

    private boolean isSuper;

    // 收藏
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<House> likedHouses;

    // 記錄
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<House> houseSearchHistory;

    // constructor
    public User() {
    }

    public User(String email, String hashedPassword, String name, int gender,
            String phone, boolean isForeign) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.isSuper = false;
        this.isForeign = isForeign;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNation() {
        return nation;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }

    public String getSelfDescription() {
        return selfDescription;
    }

    public void setLikedHouses(Set<House> likedHouses) {
        this.likedHouses = likedHouses;
    }

    public Set<House> getLikedHouses() {
        return likedHouses;
    }

    public void addLikedHouse(House house) {
        this.likedHouses.add(house);
    }

    public void removeLikedHouse(House house) {
        this.likedHouses.remove(house);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<House> getOwnedHouse() {
        return ownedHouse;
    }

    public void setOwnedHouse(Set<House> ownedHouse) {
        this.ownedHouse = ownedHouse;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIsForeign(boolean isForeign) {
        this.isForeign = isForeign;
    }

    public boolean getIsForeign() {
        return isForeign;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean aSuper) {
        isSuper = aSuper;
    }

    public void setHouseSearchHistory(Set<House> houseSearchHistory) {
        this.houseSearchHistory = houseSearchHistory;
    }

    public Set<House> getHouseSearchHistory() {
        return houseSearchHistory;
    }

    public void addHouseSearchHistory(House house) {
        this.houseSearchHistory.add(house);
    }

}
