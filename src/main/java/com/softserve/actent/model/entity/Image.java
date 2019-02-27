package com.softserve.actent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "image")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "file_path", unique = true)
    private String filePath;

    @NonNull
    @Column(name = "hash", unique = true, length = 256)
    @Length(max = 256, message = "Too long")
    private String hash;
}
