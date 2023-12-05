package com.example.demo.repository;

import com.example.demo.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    // You can add custom queries or methods if needed
    List<MemberEntity> findByID(String id);
    List<MemberEntity> findByRNN(String rnn);
    List<MemberEntity> findByEMAIL(String email);
    boolean existsByRNN(String rnn);
    boolean existsByEMAIL(String EMAIL);
    
    
}