package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Base64;

@Entity
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    @SequenceGenerator(name = "post_sequence", sequenceName = "POST_SEQUENCE", allocationSize = 1)
    private Integer ID;

    private String CONTENT;

    @Lob
    private byte[] IMAGEURL;
    
    @Transient
    private String imageBase64; // 추가

    // Getter and Setter methods
    // Post.java
    
    @Column(name = "UN", nullable = false)
    private String UN;

// Getter and Setter methods

    public String getUN() {
    return UN;
}

public void setUN(String username) {
        this.UN = username;
    }

    // 추가
    public Post(String content, byte[] imageBytes, String userName) {
        this.CONTENT = content;
        this.IMAGEURL = imageBytes;
        this.UN = userName;
        if (imageBytes != null) {
            this.imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        }
    }
    


    public Integer getId() {
        return this.ID;
    }

    public String getContent() {
        return this.CONTENT;
    }

    public byte[] getImageUrl() {
        return this.IMAGEURL;
    }
    
    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    // 기본 생성자 (필수)
    public Post() {
    }
}
