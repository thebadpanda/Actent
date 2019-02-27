package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "access_types")
public class AccessType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    @NotBlank(message = "Review text can`t be empty.")
    String type;
}
