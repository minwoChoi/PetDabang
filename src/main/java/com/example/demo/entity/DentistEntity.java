package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "DENTIST")
public class DentistEntity {
    @Id
    @Column(unique = true)
    private String ID;
    private String DNAME;
    private String DTEL;
    private String DAYS;
    private String DTYPE;
    private String DADDRESS;
    private String REGION;
    

    public boolean matchesSearchCriteria(DentistEntity searchCriteria) {
        // 여기에서 필요한 검색 로직을 추가하세요.
        // 모든 필드의 값이 일치해야 한다고 가정합니다.

        boolean matchesID = (searchCriteria.getID() == null) || this.getID().contains(searchCriteria.getID());
        boolean matchesDName = (searchCriteria.getDNAME() == null) || this.getDNAME().contains(searchCriteria.getDNAME());
        boolean matchesDTel = (searchCriteria.getDTEL() == null) || this.getDTEL().contains(searchCriteria.getDTEL());
        boolean matchesDays = (searchCriteria.getDAYS() == null) || this.getDAYS().contains(searchCriteria.getDAYS());
        boolean matchesDType = (searchCriteria.getDTYPE() == null) || this.getDTYPE().contains(searchCriteria.getDTYPE());
        boolean matchesDAddress = (searchCriteria.getDADDRESS() == null) || this.getDADDRESS().contains(searchCriteria.getDADDRESS());
        boolean matchesRegion = (searchCriteria.getREGION() == null) || this.getREGION().contains(searchCriteria.getREGION());

        return matchesID && matchesDName && matchesDTel && matchesDays && matchesDType && matchesDAddress && matchesRegion;
    }
}
