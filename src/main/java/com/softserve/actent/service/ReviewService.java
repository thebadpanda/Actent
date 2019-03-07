package com.softserve.actent.service;

import com.softserve.actent.model.entity.Review;

public interface ReviewService {

    Review addReview(Review review);

    Review getReviewById(Long reviewId);

    Review updateReviewById(Review review, Long reviewId);

    void deleteReviewById(Long reviewId);
}
