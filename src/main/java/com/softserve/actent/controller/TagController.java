package com.softserve.actent.controller;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.IncorrectStringException;
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
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addTag(@RequestBody TagDto tagDto) {

        if (tagDto.getText() == null || tagDto.getText().length() < 3) {
            throw new IncorrectStringException(ExceptionMessages.TOO_SHORT_TAG_TEXT,
                    ExceptionCode.INCORRECT_STRING);
        } else {
            Tag tag = modelMapper.map(tagDto, Tag.class);
            tag = tagService.add(tag);

            return new IdDto(tag.getId());
        }
    }

    @GetMapping(value = "/tags/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto getTagById(@PathVariable Long id) {

        Tag tag = tagService.get(id);
        return modelMapper.map(tag, TagDto.class);
    }

    @GetMapping(value = "/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDto> getTags() {

        List<Tag> tags = tagService.getAll();

        return tags.stream()
                .map(image -> modelMapper.map(image, TagDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/tags/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto updateTagById(@RequestBody TagDto tagDto, @PathVariable Long id) {

        if (tagDto.getText() == null || tagDto.getText().length() < 3) {
            throw new IncorrectStringException(ExceptionMessages.TOO_SHORT_TAG_TEXT,
                    ExceptionCode.INCORRECT_STRING);
        } else {
            Tag tag = tagService.update(modelMapper.map(tagDto, Tag.class), id);
            return modelMapper.map(tag, TagDto.class);
        }
    }

    @DeleteMapping(value = "/tags/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTagById(@PathVariable Long id) {

        tagService.delete(id);
    }
}

