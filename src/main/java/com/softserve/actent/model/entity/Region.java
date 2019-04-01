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
@Table(name = "regions", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "country_id"})})
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_REGION)
    @Length(min = NumberConstants.REGION_MIN_LENGTH,
            max = NumberConstants.REGION_MAX_LENGTH,
            message = StringConstants.REGION_LENGTH_BETWEEN_FIVE_AND_THIRTY_SYMBOLS)
    @Column(nullable = false,
            length = NumberConstants.REGION_MAX_LENGTH)
    private String name;

    @NonNull
    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<City> cities;
}
