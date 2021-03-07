package com.subha.clinicals.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subha.clinicals.model.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {

}
