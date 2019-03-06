package com.softserve.actent.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_USER_LAST_NAME)
    @Column(nullable = false)
    private String lastName;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_USER_LOGIN)
    @Column(unique = true, nullable = false)
    private String login;

    @NotNull
    @NotBlank(message = StringConstants.EMPTY_USER_EMAIL)
    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_USER_PASSWORD)
    @Column(nullable = false)
    private String password;

    @NonNull
    @JsonManagedReference
    @Column
    private LocalDate birthDate;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Image avatar;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Location location;

    @NonNull
    @Length(max = 500)
    @Column(length = 500)
    private String bio;

    @NonNull
    @ManyToMany
    @JsonBackReference
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
    @JsonBackReference
    private List<Event> events;

    @NonNull
    @JsonBackReference
    @OneToMany
    @JoinColumn
    private List<Review> reviews;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "user_chat",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "chat_id")})
    private List<Chat> bannedChats;

    @Enumerated(EnumType.STRING)
    private Role role;
}
