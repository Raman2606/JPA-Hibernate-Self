package com.example.hibernate.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.hibernate.jpa.entities.Course;
import com.example.hibernate.jpa.entities.Review;

@Repository
@Transactional
public class ReviewRepository {

	
	@PersistenceContext
	EntityManager entityManager;
	

	Logger logger= LoggerFactory.getLogger(this.getClass());
	
	public Review getReviewDetials(long reviewId) {
		return entityManager.find(Review.class, reviewId);
	}
	
	/**
	 * as the relationship is many to one, the fetch type is eager by def
	 * can be made lazy by adding FetchType.Lazy on course
	 * @param reviewId
	 * @return
	 */
	public Course getCourseFromReview(long reviewId) {
		Review review = getReviewDetials(reviewId);
		return review.getCourse();
	}

}
