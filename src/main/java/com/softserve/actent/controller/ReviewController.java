package com.softserve.actent.controller;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.IncorrectInputDataException;
import com.softserve.actent.exceptions.validation.IncorrectStringException;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api/v1")
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
    public IdDto addReview(@RequestBody CreateReviewDto addReviewDto) {

        if (addReviewDto.getText() == null || addReviewDto.getText().isEmpty()) {

            log.error(ExceptionMessages.NO_REVIEW_TEXT);
            throw new IncorrectStringException(ExceptionMessages.NO_REVIEW_TEXT, ExceptionCode.INCORRECT_STRING);
        } else if (addReviewDto.getScore() == null) {

            log.error(ExceptionMessages.NO_REVIEW_SCORE);
            throw new IncorrectInputDataException(ExceptionMessages.NO_REVIEW_SCORE, ExceptionCode.VALIDATION_FAILED);
        } else if (addReviewDto.getScore() < 1 || addReviewDto.getScore() > 5) {

            log.error(ExceptionMessages.BAD_REVIEW_SCORE);
            throw new IncorrectInputDataException(ExceptionMessages.BAD_REVIEW_SCORE, ExceptionCode.VALIDATION_FAILED);
        } else {

            Review review = modelMapper.map(addReviewDto, Review.class);
            review = reviewService.add(review);

            return new IdDto(review.getId());
        }
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
    public ReviewDto updateReview(@RequestBody CreateReviewDto updateReviewDto, @PathVariable Long id) {

        if (updateReviewDto.getText() == null || updateReviewDto.getText().isEmpty()) {

            log.error(ExceptionMessages.NO_REVIEW_TEXT);
            throw new IncorrectStringException(ExceptionMessages.NO_REVIEW_TEXT, ExceptionCode.INCORRECT_STRING);
        } else if (updateReviewDto.getScore() == null) {

            log.error(ExceptionMessages.NO_REVIEW_SCORE);
            throw new IncorrectInputDataException(ExceptionMessages.NO_REVIEW_SCORE, ExceptionCode.VALIDATION_FAILED);
        } else if (updateReviewDto.getScore() < 1 || updateReviewDto.getScore() > 5) {

            log.error(ExceptionMessages.BAD_REVIEW_SCORE);
            throw new IncorrectInputDataException(ExceptionMessages.BAD_REVIEW_SCORE, ExceptionCode.VALIDATION_FAILED);
        } else {
            Review review = reviewService.update(modelMapper.map(updateReviewDto, Review.class), id);
            return modelMapper.map(review, ReviewDto.class);
        }
    }

    @DeleteMapping(value = "/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {

        reviewService.delete(id);
    }
}
