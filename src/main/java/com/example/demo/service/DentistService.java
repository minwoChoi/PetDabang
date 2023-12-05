package com.example.demo.service;

import com.example.demo.entity.DentistEntity;
import java.util.List;

public interface DentistService {

    List<DentistEntity> getAllDentists();

    List<DentistEntity> getDentists(DentistEntity searchCriteria);

    List<DentistEntity> searchDentists(DentistEntity searchCriteria);

    boolean containsKeyword(DentistEntity dentist, String keyword);

    boolean matchesSearchCriteria(DentistEntity dentist, DentistEntity searchCriteria);

    List<DentistEntity> searchDentistsByRegion(String region);

    DentistEntity getDentistById(String dentistId);
}
