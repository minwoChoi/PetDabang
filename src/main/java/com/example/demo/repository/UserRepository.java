package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserBoard; // 패키지 이름을 적절히 조정하세요
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.UserBoard;
@Repository
public interface UserRepository extends JpaRepository<UserBoard, Integer> {
    
    Optional<UserBoard> findByUSERNAME(String username); // 메서드명을 소문자로 변경
    
    // 추가적인 메서드가 필요하다면 여기에 선언할 수 있습니다.
}
