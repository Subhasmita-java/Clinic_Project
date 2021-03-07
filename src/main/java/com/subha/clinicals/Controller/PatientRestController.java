package com.subha.clinicals.Controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subha.clinicals.model.ClinicalData;
import com.subha.clinicals.model.Patient;
import com.subha.clinicals.repos.PatientRepository;
import com.subha.clinicals.util.BMICalculator;

@RestController
@RequestMapping("/apis")
@CrossOrigin
public class PatientRestController {
	PatientRepository patientRepo;
	Map<String, String> filters = new HashMap<String, String>();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientRestController.class);
	@Autowired
	public PatientRestController(PatientRepository patientRepo) {
		this.patientRepo = patientRepo;
	}
	@GetMapping("/patient")
	public List<Patient> getAllPatientData() {
		return patientRepo.findAll();
	}
	@GetMapping("/patient/{id}")
	public Patient findPatient(@PathVariable("id") long id){
		Patient patient = patientRepo.findById(id).get();
		return patient;
	}
	@PostMapping("/patient")
	public Patient savePatient(@RequestBody Patient patient) {
		return patientRepo.save(patient);
	}
	@GetMapping("/patient/analyze/{id}")
	public Patient analyzePatient(@PathVariable("id") long id) {
		Patient patient = patientRepo.findById(id).get();
		List<ClinicalData> clinicaldata = patient.getClinicalData();
		List<ClinicalData> duplicateClinicalData = new ArrayList<ClinicalData>(clinicaldata);
		for(ClinicalData eachEntry:duplicateClinicalData) {
			if(filters.containsKey(eachEntry.getComponentName())) {
				clinicaldata.remove(eachEntry);
				continue;
			}else {
				filters.put(eachEntry.getComponentName(), null);
			}
			
			BMICalculator.calculateBMI(clinicaldata, eachEntry);
		}
		filters.clear();
		return patient;
	}
	
}
