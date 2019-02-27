package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "messagetype")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MessageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "type", unique = true)
    private String type;
}
