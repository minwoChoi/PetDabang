package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MemberEntity;

@Service
public interface MemberService {
    void registerMember(MemberEntity member);
    boolean isAnyDuplicate(MemberEntity member);
    boolean isDuplicateById(MemberEntity member);
    boolean isDuplicateByEmail(MemberEntity member);
    boolean isDuplicateByRNN(MemberEntity member);
    boolean authenticateUser(String id, String password);
    MemberEntity getMemberById(String id);
    
}