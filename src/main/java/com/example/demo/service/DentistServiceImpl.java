package com.example.demo.service;

import com.example.demo.entity.DentistEntity;
import com.example.demo.repository.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// DentistServiceImpl.java
@Service
public class DentistServiceImpl implements DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    @Override
    public List<DentistEntity> searchDentistsByRegion(String region) {
        return dentistRepository.findByREGIONContainingIgnoreCase(region);
    }

    @Override
    public List<DentistEntity> getAllDentists() {
        // 여기에 모든 치과 의사를 가져오는 로직을 추가하세요.
        return dentistRepository.findAll();
    }

    @Override
    public DentistEntity getDentistById(String dentistId) {
        // 여기에 특정 ID에 해당하는 치과 의사를 가져오는 로직을 추가하세요.
        // 예를 들어, dentistRepository.findById(dentistId).orElse(null);
        return null; // 일단은 null을 반환하도록 했습니다.
    }

    @Override
    public List<DentistEntity> getDentists(DentistEntity searchCriteria) {
        // 여기에 기준에 따라 치과 의사를 가져오는 로직을 추가하세요.
        // 예를 들어, dentistRepository.findByDNAMEContainingIgnoreCase(searchCriteria.getDNAME());
        return dentistRepository.findAll(); // 임시로 모든 치과 의사를 반환하도록 했습니다.
    }

    @Override
    public List<DentistEntity> searchDentists(DentistEntity searchCriteria) {
        // 여기에 검색 기준에 따라 치과 의사를 가져오는 로직을 추가하세요.
        // 예를 들어, dentistRepository.findByDAYSContainingIgnoreCase(searchCriteria.getDAYS());
        return dentistRepository.findAll(); // 임시로 모든 치과 의사를 반환하도록 했습니다.
    }

    @Override
    public boolean containsKeyword(DentistEntity dentist, String keyword) {
        // 여기에 필드가 특정 키워드를 포함하는지 확인하는 로직을 추가하세요.
        return false;
    }

    @Override
    public boolean matchesSearchCriteria(DentistEntity dentist, DentistEntity searchCriteria) {
        // 여기에 필요한 검색 로직을 추가하세요.
        return false;
    }
}
