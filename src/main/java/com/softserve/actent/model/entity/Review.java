package com.softserve.actent.model.entity;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.constant.NumberConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = ExceptionMessages.REVIEW_NO_TEXT)
    @Column(nullable = false, length = NumberConstants.REVIEW_TEXT_MAX_LENGTH)
    @Length(min = NumberConstants.REVIEW_TEXT_MIN_LENGTH, max = NumberConstants.REVIEW_TEXT_MAX_LENGTH)
    private String text;

    @NonNull
    @NotNull(message = ExceptionMessages.REVIEW_NO_SCORE)
    @Min(value = NumberConstants.MIN_SCORE_VALUE, message = ExceptionMessages.REVIEW_BAD_SCORE)
    @Max(value = NumberConstants.MAX_SCORE_VALUE, message = ExceptionMessages.REVIEW_BAD_SCORE)
    @Column(nullable = false)
    private Integer score;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User author;
}
