package com.nik.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nik.ecom.entity.FAQ;
import com.nik.ecom.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
	List<Review> findAllByProductId(Long productId);

}
