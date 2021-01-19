package com.example.hibernate.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.hibernate.jpa.entities.Passport;
import com.example.hibernate.jpa.entities.Student;

@Repository
@Transactional
public class StudentRepository {

	
	@PersistenceContext
	EntityManager entityManager;
	

	Logger logger= LoggerFactory.getLogger(this.getClass());
	
	public void saveStudentWithPassport() {
		//passport is saved first owing to the fact that student owns passport, hence passport should be present before persis
		Passport passport = new Passport("P12350");
		entityManager.persist(passport);
		logger.info("Passport saved {}", passport);
		Student student = new Student("Nick");
		student.setPassport(passport);
		entityManager.persist(student);
		entityManager.flush();
		Student s =new Student("Thoor");
//		s.setId(student.getId());
		entityManager.persist(s);
		
		// this will cause an additional update query to be triggered, as change are made to an object which is still under em's watch
		//student.setPassport(passport);
		logger.info("Student saved {}", student);

	}
	
	/**
	 * by default one to one relationships are eager fetched. 
	 * To see this in action remove FEtchType.LAZY on passport property in Student class
	 */
	
	public Student fetchStudentWithPasport(long studentId ) {
		
		// single select query is triggered which automatically does left join on passport table(EAGER fetch)
		Student s=  entityManager.find(Student.class, studentId);
		Hibernate.initialize(s.getPassport());
		return s;
		
		
		/*			Query triggered 
		 * 
    select
        student0_.id as id1_3_0_,
        student0_.name as name2_3_0_,
        student0_.passport_id as passport3_3_0_,
        passport1_.id as id1_1_1_,
        passport1_.passport_number as passport2_1_1_ 
    from
        student student0_ 
    left outer join
        passport passport1_ 
            on student0_.passport_id=passport1_.id 
    where
        student0_.id=?
        
        
        
		 */
		
	}

	public Student fetchStudent(long studentId) {
		
		Student student = entityManager.find(Student.class, studentId);
		student.setPassport(null);
		return student;
	}

}
