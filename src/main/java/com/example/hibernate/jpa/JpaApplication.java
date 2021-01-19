package com.example.hibernate.jpa;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.hibernate.jpa.entities.Course;
import com.example.hibernate.jpa.entities.Review;
import com.example.hibernate.jpa.entities.Student;
import com.example.hibernate.jpa.inheritancemapping.repo.EmployeeRepository;
import com.example.hibernate.jpa.repository.CourseBasicRepository;
import com.example.hibernate.jpa.repository.PassportRepository;
import com.example.hibernate.jpa.repository.ReviewRepository;
import com.example.hibernate.jpa.repository.StudentRepository;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

	@Autowired
	CourseBasicRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	PassportRepository passportRepository;

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		boolean basicJPAWithoutMappingsFlag = false;
		boolean jpaWithOneToOneMappingFlag = false;
		boolean jpaWithManyToOneMappingFlag = false;
		boolean jpaWithOneToManyMappingFlag = false;
		boolean jpaWithManyToManyMappingFlag = false;
		boolean inheritanceMappingFlag=true;

		if (jpaWithManyToManyMappingFlag) {
			jpaWithManyToManyMapping();
		}

		if (basicJPAWithoutMappingsFlag) {
			basicJPAWithoutMappings();
		}

		if (jpaWithOneToOneMappingFlag) {
			jpaWithOneToOneMapping();
		}

		if (jpaWithManyToOneMappingFlag) {
			jpaWithManyToOneMapping();
		}

		if (jpaWithOneToManyMappingFlag) {
			jpaWithOneToManyMapping();
		}
		if (inheritanceMappingFlag) {
			inheritanceMappings();
			
		}
	}

	private void inheritanceMappings() {
		employeeRepository.saveEmployee();
		logger.info("Employees present -> {}",employeeRepository.getAllEmployees());
		
		
	}

	private void jpaWithManyToManyMapping() {
		// TODO Auto-generated method stub

	}

	// @Transactional
	private void jpaWithOneToManyMapping() {
		logger.info("adding reviews for 1001");
		courseRepository.addReviewOfCourse(1001l, Arrays.asList(new Review(4007l, "ok", "2")));
		logger.info("Reviws for the course 1001 -> {}", courseRepository.getReviewsOfCourse(1001l));

	}

	private void jpaWithManyToOneMapping() {
		// ManyToOne both eager and lazy
		logger.info("Get course from review 4001 -> {}", reviewRepository.getCourseFromReview(4001l));

	}

	private void jpaWithOneToOneMapping() {
		studentRepository.saveStudentWithPassport();
		Student fetchStudentWithPasport = studentRepository.fetchStudentWithPasport(2001l);
		logger.info("Fetch all details of student  2001  with passport -> {}; passport details -> {}",
				fetchStudentWithPasport, fetchStudentWithPasport.getPassport());
		Student fetchJustStudent = studentRepository.fetchStudent(2002l);
		logger.info("fetchJustStudent 2002: Student details -> {} ; Passport Details -> {}", fetchJustStudent,
				fetchJustStudent.getPassport());
		logger.info("Fetching just passport 3001-> {}", passportRepository.fetchJustPassport(3001l, true));
		// we cannot lazily fetch parent from child as hibernate fails to proxy single
		// nullable constraint. there are way arounds for this like we can go for
		// bytecode instruimentation
	}

	private void basicJPAWithoutMappings() {
		logger.info("Checking for couse with id 1001 -> {}", courseRepository.findById(1001l));
		logger.info("Deleting course with id 1001 -> {}", "trying to delete");
		courseRepository.deleteById(1001l);
		logger.info("Checking for couse with id 1001 -> {}", courseRepository.findById(1001l));
		logger.info("Inserting course with name Spring DATA JPA");
		courseRepository.saveOrUpdate(new Course("Spring DATA JPA"));
		logger.info("Updating course with name Spring DATA JPA to Spring DATA JPA IN 100 STEPS");
		courseRepository.saveOrUpdate(new Course(1, "Spring DATA JPA IN 100 STEPS"));

		courseRepository.findByIdThroughJPQL(1001l);
		courseRepository.findByIdThroughTypedJPQLQuery(1002l);
		courseRepository.findThroughJPQLnamedQuery(1003l);
		courseRepository.findByIdThroughJPQLnamedQuery(1004l);
	}

}
