package com.group18.rental_web.model;


import com.group18.rental_web.utils.ImageUtils;

import javax.persistence.*;

@Entity
@Table(name = "house_image")
public class HouseImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Lob
    // 預設length只有250
    @Column(length = 10000)
    private byte[] imageByte;

//    private String base64;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    public HouseImage() {
    }

//    public HouseImage(House house, String base64) {
//        this.house = house;
//        this.base64 = base64;
//    }
    public HouseImage(House house, byte[] imageByte) {
        this.house = house;
        this.imageByte = imageByte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public House getHouse() {
        return house;
    }

    public String getBase64() {
        return ImageUtils.getBase64String(imageByte);
    }
}
