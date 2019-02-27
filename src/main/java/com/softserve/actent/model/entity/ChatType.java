package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name="chat_types")
@NoArgsConstructor
public class ChatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message="type can`t be empty.")
    @Column(name="type",unique = true)
    private String type;

}
