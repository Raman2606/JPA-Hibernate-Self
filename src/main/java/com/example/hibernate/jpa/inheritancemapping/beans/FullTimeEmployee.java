package com.example.hibernate.jpa.inheritancemapping.beans;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Full time")
public class FullTimeEmployee extends Employee {
	
	private BigDecimal salary;

	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}
	
	protected FullTimeEmployee() {
		
	}

	public BigDecimal getHourlyWage() {
		return salary;
	}

	public void setHourlyWage(BigDecimal hourlyWage) {
		this.salary = hourlyWage;
	}

	@Override
	public String toString() {
		return "FullTimeEmployee [salary=" + salary + ",name=" + getName() + ", id=" + getId() + "]";
	}
	
}
