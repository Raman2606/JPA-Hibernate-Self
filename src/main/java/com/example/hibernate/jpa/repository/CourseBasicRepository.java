package com.example.hibernate.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hibernate.jpa.entities.Course;
import com.example.hibernate.jpa.entities.Review;

@Repository
@Transactional
public class CourseBasicRepository {

	
	@PersistenceContext
	EntityManager entityManager;
	

	Logger logger= LoggerFactory.getLogger(this.getClass());
	public Course findById(long courseId) {
		Course course =  entityManager.find(Course.class, courseId);
		return course;
	}
	
//	@Transactional
	public void deleteById(long courseId) {
		Course course = findById(courseId);
		if(course !=null) {
			entityManager.remove(course);
		}
	}
	
	
	public void saveOrUpdate(Course course) {
		entityManager.merge(course);
//		entityManager.refr
	}
	
	public void findByIdThroughJPQL(long courseId) {
		Query query = entityManager.createQuery("Select c from Course c where id=:id");
		query.setParameter("id", 1001l);
		List resultList = query.getResultList();
		logger.info("Result through JPQL -> {}" , resultList);
	}

	public void findByIdThroughTypedJPQLQuery(long l) {
		// TODO Auto-generated method stub
		TypedQuery<Course> courseByIdQuery = entityManager.createQuery("Select c from Course c where id=:id", Course.class);
		courseByIdQuery.setParameter("id", l);
		List<Course> resultList = courseByIdQuery.getResultList();
		logger.info("Result through JPQL Typed Query -> {}" , resultList);
	}
	
	
	
	public void findThroughJPQLnamedQuery(long courseId) {
		Query query = entityManager.createNamedQuery("get_all_courses");
		List resultList = query.getResultList();
		logger.info("Result through JPQL Named Query -> {}" , resultList);
	}

	public void findByIdThroughJPQLnamedQuery(long courseId) {
		Query query = entityManager.createNamedQuery("get_all_courses_by_id");
		query.setParameter("id", courseId);
		List resultList = query.getResultList();
		logger.info("Result through JPQL Named Query  by id -> {}" , resultList);
	}
	
	/**
	 * by default one to many is lazy fetch and hence once the getReviws id
	 * exceuted, a qyuery gets executed to get the collective result
	 * 
	 * @param courseId
	 * @return
	 */

	public List<Review> getReviewsOfCourse(long courseId) {
		Course course = findById(courseId);
		Hibernate.initialize(course.getReviews());
		return course.getReviews();
	}
	
	public void addReviewOfCourse(long courseId, List<Review> reviews) {
		Course c = findById(courseId);
		for(Review review :reviews ) {
			review.setCourse(c);
			c.addReview(review);
			entityManager.persist(review);
		}
	}
}
