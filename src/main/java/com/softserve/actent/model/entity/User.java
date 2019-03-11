package com.softserve.actent.model.entity;

import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_USER_FIRST_NAME)
    @Column(nullable = false, length = 30)
    private String firstName;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_USER_LAST_NAME)
    @Column(nullable = false, length = 30)
    private String lastName;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_USER_LOGIN)
    @Column(unique = true, nullable = false, length = 30)
    private String login;

    @NotNull
    @NotBlank(message = StringConstants.EMPTY_USER_EMAIL)
    @Column(unique = true, nullable = false, length = 40)
    @Email
    private String email;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_USER_PASSWORD)
    @Column(nullable = false, length = 30)
    private String password;

    @NonNull
    @Column
    private LocalDate birthDate;

    @NonNull
    @ManyToOne
    private Image avatar;

    @NonNull
    @ManyToOne
    private Location location;

    @NonNull
    @Length(max = 500)
    @Column(length = 500)
    private String bio;

    @NonNull
    @ManyToMany
    @JoinTable(name = "user_categories",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "interests_id")})
    private List<Category> interests;


    @NonNull
    public enum sex {
        MALE, FEMALE
    }

    @NonNull
    @ManyToMany
    private List<Event> events;

    @NonNull
    @OneToMany
    @JoinColumn
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(name = "user_chat",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "chat_id")})
    private List<Chat> bannedChats;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role role;
}
