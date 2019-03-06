package com.softserve.actent.service.impl;

import com.softserve.actent.model.entity.Review;
import com.softserve.actent.repository.ReviewRepository;
import com.softserve.actent.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Review addReview(Review review) {

        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long reviewId) {

        if (reviewRepository.findById(reviewId).isPresent()) {
            return reviewRepository.findById(reviewId).get();
        } else {
            return null;
        }
    }

    @Override
    public Review updateReviewById(Review review, Long reviewId) {

        if (reviewRepository.findById(reviewId).isPresent()) {
            review.setId(reviewId);
            return reviewRepository.save(review);
        } else {
            return null;
        }
    }

    @Override
    public void deleteReviewById(Long reviewId) {

        if(reviewRepository.findById(reviewId).isPresent()) {
            reviewRepository.deleteById(reviewId);
        }

        // TODO: else throw exception or so
    }
}
