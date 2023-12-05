package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import java.util.Date;

@Data
@Entity
@Table(name = "RESERVATION")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    @SequenceGenerator(name = "reservation_seq", sequenceName = "reservation_seq", allocationSize = 1)
    @Column(name = "RID")
    private Long rid;

    @Column(name = "PATIENTID")
    private String patientId;

    @ManyToOne
    @JoinColumn(name = "PATIENTID", referencedColumnName = "ID", insertable = false, updatable = false)
    private MemberEntity patient;

    @Column(name = "PATIENTNAME")
    private String patientName;

    @Column(name = "DENTISTID")
    private String dentistId;

    @ManyToOne
    @JoinColumn(name = "DENTISTID", referencedColumnName = "ID", insertable = false, updatable = false)
    private DentistEntity dentist;

    @Column(name = "DENTISTNAME")
    private String dentistName;

    @Column(name = "RDATE")
    private LocalDateTime rDate;
    
    @Column(name = "DISABILITY")
    private String DISABILITY;
    
    
}
