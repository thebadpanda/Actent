package com.softserve.actent.model.entity;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "locations", uniqueConstraints = {@UniqueConstraint(columnNames = {"address", "city_id"})})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_LOCATION)
    @Length(min = NumberConstants.LOCATION_MIN_LENGTH,
            max = NumberConstants.LOCATION_MAX_LENGTH,
            message = StringConstants.LOCATION_LENGTH_BETWEEN_FIVE_AND_FIFTY_SYMBOLS)
    @Column(nullable = false,
            length = NumberConstants.LOCATION_MAX_LENGTH)
    private String address;

    @NonNull
    @ManyToOne
    private City city;
}
