package com.softserve.actent.model.entity;

import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = StringConstants.TITLE_SHOULD_NOT_BE_BLANK)
    @Column(nullable = false)
    private String title;

    @NonNull
    @NotBlank(message = StringConstants.DESCRIPTION_SHOULD_NOT_BE_BLANK)
    @Column(nullable = false)
    private String description;

    @NonNull
    private boolean satisfied;

    @NonNull
    @ManyToOne
    private User assignedUser;

    @NonNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private Event assignedEvent;

}
