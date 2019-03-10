package com.softserve.actent.model.entity;

import com.softserve.actent.constant.StringConstants;
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
    @NotBlank(message = StringConstants.CATEGORY_NOT_BE_BLANK)
    @Length(max = 30, message = StringConstants.CATEGORY_NO_LONGER_THAN_THIRTY_SYMBOLS)
    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();

    public Category(@NonNull @NotBlank(message = StringConstants.CATEGORY_NOT_BE_BLANK) @Length(max = 30, message = StringConstants.CATEGORY_NO_LONGER_THAN_THIRTY_SYMBOLS) String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
}
