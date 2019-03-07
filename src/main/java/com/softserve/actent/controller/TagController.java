package com.softserve.actent.controller;

import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.TagDto;
import com.softserve.actent.model.entity.Tag;
import com.softserve.actent.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class TagController {

    private final TagService tagService;

    private final ModelMapper modelMapper;

    @Autowired
    public TagController(TagService tagService, ModelMapper modelMapper) {
        this.tagService = tagService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/tags")
    public ResponseEntity<IdDto> addTag(@RequestBody TagDto tagDto) {

        Tag tag = modelMapper.map(tagDto, Tag.class);
        tag = tagService.add(tag);

        return new ResponseEntity<>(new IdDto(tag.getId()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/tags/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable Long id) {

        Tag tag = tagService.get(id);
        return new ResponseEntity<>(modelMapper.map(tag, TagDto.class), HttpStatus.OK);
    }

    @GetMapping(value = "/tags")
    public ResponseEntity<List<TagDto>> getTags() {

        List<Tag> tags = tagService.getAll();
        List<TagDto> imagesDto = tags.stream()
                .map(image -> modelMapper.map(image, TagDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(imagesDto, HttpStatus.OK);
    }

    @PutMapping(value = "/tags/{id}")
    public ResponseEntity<TagDto> updateTagById(@RequestBody TagDto tagDto, @PathVariable Long id) {

        Tag tag = tagService.update(modelMapper.map(tagDto, Tag.class), id);
        return new ResponseEntity<>(modelMapper.map(tag, TagDto.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/tags/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable Long id) {

        tagService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

