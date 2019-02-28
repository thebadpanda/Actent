package com.softserve.actent.model.entity;

import com.softserve.actent.resources.StringConstants;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = StringConstants.CITY_SHOULD_NOT_BE_BLANK)
    @Length(max = 30, message = "Too long")
    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}