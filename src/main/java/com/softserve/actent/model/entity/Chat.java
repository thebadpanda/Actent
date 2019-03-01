package com.softserve.actent.model.entity;

import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "chat")
    private Set<Message> messages;

    @NonNull
    @NotNull(message = StringConstants.CHAT_TYPE_NOT_BE_BLANK)
    @Enumerated(EnumType.STRING)
    private ChatType type;

    @NonNull
    @Column(name = "banned_in_chat")
    @ManyToMany(fetch=FetchType.LAZY,
            mappedBy = "bannedChats")
    private Set<User> bannedUsers = new HashSet<>();

}
