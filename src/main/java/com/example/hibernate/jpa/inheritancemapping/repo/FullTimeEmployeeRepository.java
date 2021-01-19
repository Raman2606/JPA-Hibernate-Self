package com.example.hibernate.jpa.inheritancemapping.repo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FullTimeEmployeeRepository {

	@Autowired
	EntityManager entityManager;
	
	
}
