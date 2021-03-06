package com.example.hibernate.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private double moneyInHand;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;
	
	protected Student() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public Student(String name) {
		this.name = name;
	}
	

	public Student(String name, double moneyInHand) {
		super();
		this.name = name;
		this.moneyInHand = moneyInHand;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", moneyInHand=" + moneyInHand + "]";
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMoneyInHand() {
		return moneyInHand;
	}

	public void setMoneyInHand(double moneyInHand) {
		this.moneyInHand = moneyInHand;
	}

	
	
}
	
	
