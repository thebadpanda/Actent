package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String messageContent;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime sendTime;

    @ManyToOne
    private Image image;

    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User sender;

    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Chat chat;

}
