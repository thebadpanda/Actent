package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private MessageType messageType;

    @OneToOne(cascade = CascadeType.ALL)
    private MessageBody messageBody;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

}
