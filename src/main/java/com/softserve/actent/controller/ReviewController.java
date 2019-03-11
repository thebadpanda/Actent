package com.softserve.actent.controller;

import com.softserve.actent.model.dto.CreateReviewDto;
import com.softserve.actent.model.dto.ReviewDto;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.entity.Review;
import com.softserve.actent.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addReview(@RequestBody CreateReviewDto addReviewDto) {

        Review review = modelMapper.map(addReviewDto, Review.class);
        review = reviewService.add(review);

        return new IdDto(review.getId());
    }

    @GetMapping(value = "/reviews/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto getReview(@PathVariable Long id) {

        Review review = reviewService.get(id);
        return modelMapper.map(review, ReviewDto.class);
    }

    @GetMapping(value = "/reviews")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDto> getAllReviews() {

        List<Review> reviews = reviewService.getAll();
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/reviews/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto updateReview(@RequestBody CreateReviewDto reviewDto, @PathVariable Long id) {

        Review review = reviewService.update(modelMapper.map(reviewDto, Review.class), id);
        return modelMapper.map(review, ReviewDto.class);
    }

    @DeleteMapping(value = "/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {

        reviewService.delete(id);
    }
}
