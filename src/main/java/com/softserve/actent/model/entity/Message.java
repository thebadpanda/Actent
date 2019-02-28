package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private MessageType messageType;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private MessageBody messageBody;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

}
