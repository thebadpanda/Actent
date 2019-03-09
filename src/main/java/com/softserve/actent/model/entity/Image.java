package com.softserve.actent.model.entity;

import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = StringConstants.IMAGE_FILE_PATH_SHOULD_NOT_BE_BLANK)
    @Column(name = "file_path", nullable = false,  unique = true, length = 256)
    @Length(max = 256, message = StringConstants.TOO_LONG)
    private String filePath;

    @NonNull
    @NotBlank(message = StringConstants.IMAGE_HASH_SHOULD_NOT_BE_BLANK)
    @Column(nullable = false, unique = true, length = 64)
    @Length(min = 64, max = 64, message = StringConstants.IMAGE_HASH_MUST_BE_OF_EXACT_LENGHT_64)
    private String hash;
}
