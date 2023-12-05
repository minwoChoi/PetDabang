// ReservationServiceImpl.java
package com.example.demo.service;

import com.example.demo.entity.ReservationEntity;
import com.example.demo.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    // 생성자 주입 등을 사용하여 Repository를 주입받도록 설정
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void makeReservation(ReservationEntity reservationEntity) {
        // 예약 로직 처리
        reservationRepository.save(reservationEntity);
    }

    @Override
    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

    // 추가적인 메서드들은 여기에 있을 것이라고 가정합니다.
}
