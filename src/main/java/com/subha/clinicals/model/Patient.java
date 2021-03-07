package com.subha.clinicals.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long id;
	private String firstName;
	private String lastName;
	private int age;
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER,mappedBy = "patient")
	private List<ClinicalData> clinicalData;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<ClinicalData> getClinicalData() {
		return clinicalData;
	}
	public void setClinicalData(List<ClinicalData> clinicalData) {
		this.clinicalData = clinicalData;
	}

}
