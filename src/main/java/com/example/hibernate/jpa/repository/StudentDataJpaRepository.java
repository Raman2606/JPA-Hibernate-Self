package com.example.hibernate.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hibernate.jpa.entities.Student;

public interface StudentDataJpaRepository extends JpaRepository<Student, Long> {

	public List<Student> findByName(String name);
	public List<Student> findByNameContaining(String substring);
	public List<Student> findByNameEndingWith(String endingWith);
	public List<Student> findByNameStartingWith(String startingWith);
	public List<Student> findByNameOrderByIdDesc(String substring);
	public List<Student> findByNameLike(String substring);
	public List<Student> findByNameAndMoneyInHand(String name, double moneyInHand);
	public List<Student> findByMoneyInHandGreaterThan(double moneyInHand);
	public List<Student> findByMoneyInHandLessThan(double moneyInHand);
	
	
}
