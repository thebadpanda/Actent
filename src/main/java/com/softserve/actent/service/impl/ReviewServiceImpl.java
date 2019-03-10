package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.Review;
import com.softserve.actent.repository.ReviewRepository;
import com.softserve.actent.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review add(Review review) {

        return reviewRepository.save(review);
    }

    @Override
    public Review get(Long reviewId) {

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        return optionalReview.orElseThrow(()
                -> new ResourceNotFoundException(ExceptionMessages.NO_REVIEW_WITH_ID, ExceptionCode.NOT_FOUND));
    }

    @Override
    public List<Review> getAll() {

        return reviewRepository.findAll();
    }

    @Override
    public Review update(Review review, Long reviewId) {

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            review.setId(reviewId);
            return reviewRepository.save(review);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.NO_REVIEW_WITH_ID, ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public void delete(Long reviewId) {

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if(optionalReview.isPresent()) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.NO_REVIEW_WITH_ID, ExceptionCode.NOT_FOUND);
        }
    }
}
