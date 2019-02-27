package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "messagetypes")
@Data
@NoArgsConstructor
public class MessageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Review text can`t be empty.")
    @Column(name = "type", unique = true)
    private String type;
}
