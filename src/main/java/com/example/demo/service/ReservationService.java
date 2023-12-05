package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ReservationEntity;
import com.example.demo.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public interface ReservationService {
    void makeReservation(ReservationEntity reservationEntity);
    List<ReservationEntity> getAllReservations();
    // 추가적인 메서드들을 필요에 따라 선언하세요.
}


