package com.example.hibernate.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.hibernate.jpa.entities.Passport;

@Repository
@Transactional
public class PassportRepository {

	
	@PersistenceContext
	EntityManager entityManager;
	

	Logger logger= LoggerFactory.getLogger(this.getClass());


	public Passport fetchJustPassport(long passportId, boolean onlyPassportFlag) {
		// TODO Auto-generated method stub
		 Passport passport = entityManager.find(Passport.class, passportId);
		 if(!onlyPassportFlag) {
			 Hibernate.initialize(passport.getStudent());
		 }
		 return passport;
	}

}
