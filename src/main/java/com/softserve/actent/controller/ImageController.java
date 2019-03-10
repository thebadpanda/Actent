package com.softserve.actent.controller;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.IncorrectStringException;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.AddImageDto;
import com.softserve.actent.model.dto.ImageDto;
import com.softserve.actent.model.entity.Image;
import com.softserve.actent.service.ImageService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class ImageController {

    private final ImageService imageService;
    private final ModelMapper modelMapper;

    @Autowired
    public ImageController(ImageService imageService, ModelMapper modelMapper) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/images")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addImage(@RequestBody AddImageDto addImageDto) {

        if (addImageDto.getHash().length() != 64) {

            log.error(ExceptionMessages.INAPPROPRIATE_HASH_LENGTH);
            throw new IncorrectStringException(ExceptionMessages.INAPPROPRIATE_HASH_LENGTH,
                    ExceptionCode.INCORRECT_STRING);
        } else if (addImageDto.getFilePath().isEmpty()) {

            log.error(ExceptionMessages.NO_IMAGE_FILEPATH);
            throw new IncorrectStringException(ExceptionMessages.NO_IMAGE_FILEPATH,
                    ExceptionCode.INCORRECT_STRING);
        } else {

            Image image = modelMapper.map(addImageDto, Image.class);
            image = imageService.add(image);

            return new IdDto(image.getId());
        }
    }

    @GetMapping(value = "/images/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImageDto getImageById(@PathVariable Long id) {

        Image image = imageService.get(id);
        return modelMapper.map(image, ImageDto.class);
    }

    @GetMapping(value = "/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageDto> getImages(@RequestParam(value = "url", required = false) String url) {

        List<ImageDto> imagesDto = new ArrayList<>();
        if (url != null) {

            Image image = imageService.getImageByFilePath(url);
            imagesDto.add(modelMapper.map(image, ImageDto.class));

            return imagesDto;
        } else {

            List<Image> images = imageService.getAll();

            for (Image image : images) {
                imagesDto.add(modelMapper.map(image, ImageDto.class));
            }

            return imagesDto;
        }
    }

    @PutMapping(value = "/images/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImageDto updateImage(@RequestBody AddImageDto addImageDto, @PathVariable Long id) {

        if (addImageDto.getHash().length() != 64) {

            log.error(ExceptionMessages.INAPPROPRIATE_HASH_LENGTH);
            throw new IncorrectStringException(ExceptionMessages.INAPPROPRIATE_HASH_LENGTH,
                    ExceptionCode.INCORRECT_STRING);
        } else if (addImageDto.getFilePath().isEmpty()) {

            log.error(ExceptionMessages.NO_IMAGE_FILEPATH);
            throw new IncorrectStringException(ExceptionMessages.NO_IMAGE_FILEPATH,
                    ExceptionCode.INCORRECT_STRING);
        } else {

            Image image = imageService.update(modelMapper.map(addImageDto, Image.class), id);
            return modelMapper.map(image, ImageDto.class);
        }
    }

    @DeleteMapping(value = "/images/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable Long id) {

        imageService.delete(id);
    }
}
