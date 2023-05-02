package com.group18.rental_web.entities.post;


import javax.persistence.*;

@Entity
@Table(name = "post_image")
public class PostImage {
    @Id
    private int id;

    @Lob
    @Column(length = 1000)
    private byte[] imageByte;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
