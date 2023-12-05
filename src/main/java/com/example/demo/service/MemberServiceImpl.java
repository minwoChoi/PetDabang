package com.example.demo.service;

import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    
    @Override
    public MemberEntity getMemberById(String id) {
        return memberRepository.findById(id).orElse(null);
    }
    
    @Override
    public void registerMember(MemberEntity member) {
        if (!isAnyDuplicate(member)) {
            memberRepository.save(member);
        }
        // Handle duplicate case if needed, e.g., throw an exception
    }
    
    @Override
    public boolean isAnyDuplicate(MemberEntity member) {
        return isDuplicateById(member) || isDuplicateByEmail(member) || isDuplicateByRNN(member);
    }

    @Override
    public boolean isDuplicateById(MemberEntity member) {
        return memberRepository.existsById(member.getID());
    }

    @Override
    public boolean isDuplicateByEmail(MemberEntity member) {
        return memberRepository.existsByEMAIL(member.getEMAIL());
    }

    @Override
    public boolean isDuplicateByRNN(MemberEntity member) {
        return memberRepository.existsByRNN(member.getRNN());
    }

    @Override
    public boolean authenticateUser(String id, String password) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(id);

        // 사용자가 존재하고, 입력된 비밀번호가 저장된 비밀번호와 일치하면 로그인 성공
        return optionalMember.isPresent() && password.equals(optionalMember.get().getPW());
    }

}
