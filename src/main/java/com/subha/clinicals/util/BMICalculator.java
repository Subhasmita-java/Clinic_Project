package com.subha.clinicals.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subha.clinicals.Controller.PatientRestController;
import com.subha.clinicals.model.ClinicalData;

public class BMICalculator {
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientRestController.class);
	
	public static void calculateBMI(List<ClinicalData> clinicaldata, ClinicalData eachEntry) {
		if(eachEntry.getComponentName().equals("hw")) {
			LOGGER.info("Its calculate hw");
			String[] heightAndWeight = eachEntry.getComponentValue().split("/");
			if(heightAndWeight != null && heightAndWeight.length>1) {
				Float heightInMeters =Float.parseFloat(heightAndWeight[0])* 0.4536F;
				Float bmi = Float.parseFloat(heightAndWeight[1])/(heightInMeters*heightInMeters);
				ClinicalData bmiData = new ClinicalData();
				bmiData.setComponentValue(Float.toString(bmi));
				clinicaldata.add(bmiData);
			}
		}
	}

}
