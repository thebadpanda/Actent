package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

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

    @NonNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "category_subcategories",
            joinColumns = {@JoinColumn(name = "category_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "subcategory_id", nullable = false)})
    private Set<Subcategory> subcategories;
}
