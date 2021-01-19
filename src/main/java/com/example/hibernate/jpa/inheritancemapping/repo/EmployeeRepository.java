package com.example.hibernate.jpa.inheritancemapping.repo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hibernate.jpa.inheritancemapping.beans.Employee;
import com.example.hibernate.jpa.inheritancemapping.beans.FullTimeEmployee;
import com.example.hibernate.jpa.inheritancemapping.beans.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	EntityManager entityManager;
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	public void saveEmployee() {
		insertEmployee(new PartTimeEmployee("Jack", new BigDecimal("50")));
		logger.info("inserted part time employee jack");
		insertEmployee(new FullTimeEmployee("James", new BigDecimal("5000")));
		logger.info("inserted full time employee james");

	}

	private void insertEmployee(Employee Employee) {

		entityManager.persist(Employee);
	}
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> getAllEmployeesQuery = entityManager.createQuery("select e from Employee e", Employee.class);
		List<Employee> employees = getAllEmployeesQuery.getResultList();
		return employees;
	}
	
	public Employee getEmployeeBId(long empId) {
		return entityManager.find(Employee.class, empId);
	}
}
