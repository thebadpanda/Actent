package com.softserve.actent.repository;

import com.softserve.actent.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository <Review, Long> {
}
