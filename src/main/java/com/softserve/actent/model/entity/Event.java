package com.softserve.actent.model.entity;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = StringConstants.TITLE_SHOULD_NOT_BE_BLANK)
    @Length(max = NumberConstants.TITLE_MAX_LENGTH, message = StringConstants.TITLE_LENGTH_IS_TO_LONG)
    @Column(nullable = false, length = 100)
    private String title;

    @Length(max = NumberConstants.MAX_VALUE_FOR_DESCRIPTION, message = StringConstants.DESCRIPTION_SHOULD_BE_BETWEEN_MIN_AND_MAX_VALUE)
    @Column(length = NumberConstants.MAX_VALUE_FOR_DESCRIPTION)
    private String description;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @NonNull
    @Column(name = "start_date", nullable = false)
    private Long startDate;

    @NonNull
    @Column(nullable = false)
    private Long duration;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    @Fetch(FetchMode.JOIN)
    private User creator;

    @NonNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Location address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    @Fetch(FetchMode.JOIN)
    private Image image;

    private Integer capacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignedEvent", orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Equipment> equipments;

    @NonNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = false)
    private AccessType accessType;

    @OneToMany(mappedBy = "event")
    private List<EventUser> eventUserList;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Category category;

    @ManyToMany
    @JoinTable(name = "event_tags",
            joinColumns = {@JoinColumn(name = "event_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false)})
    @Fetch(FetchMode.SUBSELECT)
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    @Fetch(FetchMode.JOIN)
    private Chat chat;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Review> feedback;
}
