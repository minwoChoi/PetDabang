package com.example.demo.repository;

import com.example.demo.entity.DentistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DentistRepository extends JpaRepository<DentistEntity, String> {

    List<DentistEntity> findByREGIONContainingIgnoreCase(String region);

    // 다른 쿼리 메서드들을 필요에 따라 추가할 수 있습니다.
}
