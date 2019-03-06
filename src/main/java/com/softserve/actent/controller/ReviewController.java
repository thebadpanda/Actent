package com.softserve.actent.controller;

import com.softserve.actent.model.dto.ReviewDto;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.entity.Review;
import com.softserve.actent.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/reviews")
    public ResponseEntity<IdDto> addReview(@RequestBody ReviewDto addReviewDto) {
        Review review = modelMapper.map(addReviewDto, Review.class);
        review = reviewService.addReview(review);
        return new ResponseEntity<>(new IdDto(review.getId()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/reviews/{id}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return new ResponseEntity<>(modelMapper.map(review, ReviewDto.class), HttpStatus.OK);
    }

    @PutMapping(value = "/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto, @PathVariable Long id) {

        Review review = reviewService.updateReviewById(modelMapper.map(reviewDto, Review.class), id);
        return new ResponseEntity<>(modelMapper.map(review, ReviewDto.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/reviews/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {

        reviewService.deleteReviewById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
