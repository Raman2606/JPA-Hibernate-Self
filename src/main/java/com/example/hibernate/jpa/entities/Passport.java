package com.example.hibernate.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String passportNumber;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
	private Student student;
	
	
	protected Passport() {
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String pasString) {
		this.passportNumber = pasString;
	}

	public long getId() {
		return id;
	}

	public Passport(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", PassportNumber=" + passportNumber + "]";
	}

	
	
}
	
	
