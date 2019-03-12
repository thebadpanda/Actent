package com.softserve.actent.model.entity;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cities")
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
