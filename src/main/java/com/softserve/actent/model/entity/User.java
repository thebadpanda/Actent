package com.softserve.actent.model.entity;

import com.softserve.actent.resources.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
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
    private String email;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_USER_PASSWORD)
    @Column(nullable = false)
    private String password;

    @NonNull
    @NotNull(message = StringConstants.EMPTY_USER_BIRTH_DATE)
    @Column(nullable = false)
    private Date birth_date;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Image avatar;

    @NonNull
    @NotNull(message = StringConstants.EMPTY_USER_LOCATION)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @NonNull
    @Column(length = 500)
    private String bio;

    @NonNull
    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_categories",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "interests_id")})
    private List<Category> interests;

    @NonNull
    @NotBlank(message = StringConstants.EMPTY_USER_SEX)
    @Column(nullable = false)
    private String sex;

    @NonNull
    @Column
    @ManyToMany
    @JoinTable(name = "user_events",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> events;

    @NonNull
    @Column
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private List<Review> reviews;

    @Enumerated(EnumType.STRING)
    private Role roles;
}
