package com.example.hibernate.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hibernate.jpa.entities.Student;
import com.example.hibernate.jpa.repository.StudentDataJpaRepository;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	StudentDataJpaRepository  studentDataJpaRepository;

	public List<Student> getAllStudent() {
		List<Student> findAll = studentDataJpaRepository.findAll();
		findAll.forEach(System.out::println);
		return findAll;
	}
	
	public Student getStudentById(long id) {
		Student one = studentDataJpaRepository.getOne(id);
		System.out.println(one);
		return one;
	}
	
	
	public void triggerStudentJPAMethods() {
//		System.out.println(studentDataJpaRepository.findByName("Jack"));
//		System.out.println(studentDataJpaRepository.findByNameContaining("a"));
//		System.out.println(studentDataJpaRepository.findByNameEndingWith("e"));
//		System.out.println(studentDataJpaRepository.findByNameStartingWith("J"));
//		System.out.println(studentDataJpaRepository.findByNameOrderByIdDesc("Jack"));
//		System.out.println(studentDataJpaRepository.findByNameLike("%a%"));
//		System.out.println("AND criteria result : " +studentDataJpaRepository.findByNameAndMoneyInHand("Jack",34.56));
		System.out.println("Greater than : " +studentDataJpaRepository.findByMoneyInHandGreaterThan(1000.00));
		System.out.println("Lesser than : " +studentDataJpaRepository.findByMoneyInHandLessThan(1000.00));
	}
	
	
}
