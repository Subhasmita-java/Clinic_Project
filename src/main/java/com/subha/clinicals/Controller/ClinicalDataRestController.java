package com.subha.clinicals.Controller;

import java.util.ArrayList;
import java.util.List;

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

import com.subha.clinicals.dto.ClinicalRequest;
import com.subha.clinicals.model.ClinicalData;
import com.subha.clinicals.model.Patient;
import com.subha.clinicals.repos.ClinicalDataRepository;
import com.subha.clinicals.repos.PatientRepository;
import com.subha.clinicals.util.BMICalculator;

@RestController
@RequestMapping("/apis")
@CrossOrigin
public class ClinicalDataRestController {
	@Autowired
	ClinicalDataRepository clinicalDataRepo;
	@Autowired
	PatientRepository patientRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientRestController.class);
	
	@PostMapping("/clinicaldata")
	public ClinicalData saveClinicalRecords(@RequestBody ClinicalRequest clinicalRequest) {
		ClinicalData clinicalData = new ClinicalData();
		clinicalData.setComponentName(clinicalRequest.getComponentName());
		clinicalData.setComponentValue(clinicalRequest.getComponentValue());
		Patient patient =patientRepo.findById(clinicalRequest.getPatientId()).get();
		clinicalData.setPatient(patient);
		
		return clinicalDataRepo.save(clinicalData);
	}
	@GetMapping("/clinicaldata/{patientId}/{componentName}")
	public List<ClinicalData> retriveClinicalData(@PathVariable("patientId") long patientId,
			@PathVariable("componentName")String componentName){
		Patient patient =patientRepo.findById(patientId).get();
		List<ClinicalData> clinicalData = patient.getClinicalData();
		List<ClinicalData> result = new ArrayList<ClinicalData>();
		
		for(ClinicalData eachEntry : clinicalData) {
			LOGGER.info("component name :" +eachEntry.getComponentName()+", Enter compo: "+componentName);
			if(eachEntry.getComponentName().equals(componentName)) {
				result.add(eachEntry);
				}
			else if(componentName.equals("bmi") && eachEntry.getComponentName().equals("hw")){
				LOGGER.info("it's bmi");
				BMICalculator.calculateBMI(result, eachEntry);
			}
		}
		
		return result;
	}
}
