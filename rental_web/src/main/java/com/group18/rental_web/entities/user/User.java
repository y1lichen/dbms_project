package com.group18.rental_web.entities.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.group18.rental_web.entities.house.House;

@Entity
@Table(name = "user")
public class User {
    // email
    @Id
    private String account;

    private String hashedPassword;

    //    0: male, 1: female, 2: other
    private int gender;

    private String phone;

    // 房東擁有的房子
    @OneToMany
    private Set<House> ownedHouse;

    public String getAccount() {
        return account;
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
}
