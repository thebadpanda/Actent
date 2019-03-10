package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Can't be empty")
    @Length(max = 20, message = "Too long")
    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();

    public Category(@NonNull @NotBlank(message = "Can't be empty") @Length(max = 20, message = "Too long") String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
}
