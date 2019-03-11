package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String messageContent;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime sendTime;

    @UpdateTimestamp
    private LocalDateTime lastEditTime;

    @ManyToOne
    private Image image;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Chat chat;

}
