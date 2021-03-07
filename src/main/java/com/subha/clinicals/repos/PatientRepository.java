package com.subha.clinicals.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subha.clinicals.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
