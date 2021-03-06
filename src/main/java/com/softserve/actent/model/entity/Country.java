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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_COUNTRY)
    @Length(min = NumberConstants.COUNTRY_MIN_LENGTH,
            max = NumberConstants.COUNTRY_MAX_LENGTH,
            message = StringConstants.COUNTRY_LENGTH_BETWEEN_THREE_AND_THIRTY_SYMBOLS)
    @Column(unique = true,
            nullable = false,
            length = NumberConstants.COUNTRY_MAX_LENGTH)
    private String name;

    @NonNull
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Region> regions;
}
