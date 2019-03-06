package com.softserve.actent.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @NotBlank(message = StringConstants.COUNTRY_SHOULD_NOT_BE_BLANK)
    @Length(max = 30, message = StringConstants.COUNTRY_NO_LONGER_THAN_THIRTY_SYMBOLS)
    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @NonNull
    @JsonBackReference
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Region> regions;
}
