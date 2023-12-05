package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "PATIENT")
public class MemberEntity {
    @Id
    @Column(unique = true)
    private String ID;
    @Column(unique = true)
    private String EMAIL;
    
    private String PW;
    private String NAME;
    @Column(unique = true)
    private String RNN;
    private String ADDRESS;
    private String DISABILITY;
    
        
}