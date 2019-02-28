package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Country can`t be blank")
    @Length(max = 30, message = "Too long")
    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Region> regions;
}
