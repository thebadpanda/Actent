package com.softserve.actent.model.entity;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @Column(nullable = false, length = NumberConstants.TITLE_MAX_LENGTH)
    private String title;

    @NonNull
    @Column(length = NumberConstants.DESCRIPTION_MAX_LENGTH)
    private String description;

    @NonNull
    private Boolean satisfied;

    @NonNull
    @ManyToOne
    private User assignedUser;

    @NonNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private Event assignedEvent;
}
