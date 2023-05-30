package com.group18.rental_web.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    // email
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String hashedPassword;

    private String name;

    //    0: male, 1: female, 2: other
    private int gender;

    private String phone;

    private String token;

    private boolean isForeign;

    // 房東擁有的房子
    @OneToMany
    private Set<House> ownedHouse;

    private boolean isSuper;

    @ManyToMany
    private Set<House> resideHouses;

    //constructor
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
}
