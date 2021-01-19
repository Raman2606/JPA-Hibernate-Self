package com.example.hibernate.jpa.inheritancemapping.beans;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("Part time")
public class PartTimeEmployee extends Employee {
	
	private BigDecimal hourlyWage;

	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}
	
	protected PartTimeEmployee() {
		
	}

	public BigDecimal getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	@Override
	public String toString() {
		return "PartTimeEmployee [hourlyWage=" + hourlyWage + ",name=" + getName() + ", id=" + getId() + "]";
	}
	
}
