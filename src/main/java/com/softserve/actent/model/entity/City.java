package com.softserve.actent.model.entity;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cities", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "region_id"})})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_CITY)
    @Length(min = NumberConstants.CITY_MIN_LENGTH,
            max = NumberConstants.CITY_MAX_LENGTH,
            message = StringConstants.CITY_LENGTH_BETWEEN_FOUR_AND_THIRTY_SYMBOLS)
    @Column(nullable = false,
            length = NumberConstants.CITY_MAX_LENGTH)
    private String name;

    @NonNull
    @ManyToOne
    private Region region;

    @NonNull
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Location> locations;
}
