package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "City can`t be blank")
    @Length(max = 30, message = "Too long")
    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}