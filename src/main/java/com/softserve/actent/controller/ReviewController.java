package com.softserve.actent.controller;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.IncorrectInputDataException;
import com.softserve.actent.exceptions.validation.IncorrectStringException;
import com.softserve.actent.exceptions.validation.ValidationException;
import com.softserve.actent.model.dto.CreateReviewDto;
import com.softserve.actent.model.dto.ReviewDto;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.entity.Review;
import com.softserve.actent.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(UrlConstants.API_V1)
public class ReviewController {

    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ModelMapper modelMapper) {
        this.reviewService = reviewService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addReview(@Validated @RequestBody CreateReviewDto addReviewDto) {

        Review review = modelMapper.map(addReviewDto, Review.class);
        review = reviewService.add(review);

        return new IdDto(review.getId());
    }

    @GetMapping(value = "/reviews/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDto getReview(@PathVariable @NotNull(message = ExceptionMessages.REVIEW_NO_ID)
                                   @Positive(message = ExceptionMessages.REVIEW_INNAPPROPRIATE_ID) Long id) {

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
    public ReviewDto updateReview(@Validated @RequestBody CreateReviewDto updateReviewDto,
                                  @PathVariable @NotNull(message = ExceptionMessages.REVIEW_NO_ID)
                                  @Positive(message = ExceptionMessages.REVIEW_INNAPPROPRIATE_ID) Long id) {

        Review review = reviewService.update(modelMapper.map(updateReviewDto, Review.class), id);
        return modelMapper.map(review, ReviewDto.class);
    }

    @DeleteMapping(value = "/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable
                                   @NotNull(message = ExceptionMessages.REVIEW_NO_ID)
                                   @Positive(message = ExceptionMessages.REVIEW_INNAPPROPRIATE_ID) Long id) {

        reviewService.delete(id);
    }
}
