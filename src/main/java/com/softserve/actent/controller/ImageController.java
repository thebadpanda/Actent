package com.softserve.actent.controller;

import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.ImageDto;
import com.softserve.actent.model.entity.Image;
import com.softserve.actent.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<IdDto> addImage(@RequestBody ImageDto addImageDto) {

        Image image = modelMapper.map(addImageDto, Image.class);
        image = imageService.add(image);

        return new ResponseEntity<>(new IdDto(image.getId()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/images/{id}")
    public ResponseEntity<ImageDto> getImageById(@PathVariable Long id) {

        Image image = imageService.get(id);
        return new ResponseEntity<>(modelMapper.map(image, ImageDto.class), HttpStatus.OK);
    }

    @GetMapping(value = "/images")
    public ResponseEntity<?> getImages(@RequestParam(value = "url", required = false) String url) {

        if (url != null) {

            Image image = imageService.getImageByFilePath(url);

            return new ResponseEntity<>(modelMapper.map(image, ImageDto.class), HttpStatus.OK);
        } else {

            List<Image> images = imageService.getAll();
            List<ImageDto> imagesDto = new ArrayList<>();

            for (Image image : images) {
                imagesDto.add(modelMapper.map(image, ImageDto.class));
            }

            return new ResponseEntity<>(imagesDto, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/images/{id}")
    public ResponseEntity<ImageDto> updateImage(@RequestBody ImageDto imageDto, @PathVariable Long id) {

        Image image = imageService.update(modelMapper.map(imageDto, Image.class), id);
        return new ResponseEntity<>(modelMapper.map(image, ImageDto.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/images/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {

        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
