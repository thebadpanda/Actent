package com.softserve.actent.model.entity;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @NotBlank(message = StringConstants.CATEGORY_NOT_BE_BLANK)
    @Length(max = NumberConstants.MAX_VALUE_FOR_CATEGORY_NAME, message = StringConstants.CATEGORY_NO_LONGER_THAN_THIRTY_SYMBOLS)
    @Column(unique = true, nullable = false, length = NumberConstants.MAX_VALUE_FOR_CATEGORY_NAME)
    private String name;

    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
}
