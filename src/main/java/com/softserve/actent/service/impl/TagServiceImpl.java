package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.Tag;
import com.softserve.actent.repository.TagRepository;
import com.softserve.actent.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public Tag add(Tag tag) {

        Optional<Tag> optionalTag = tagRepository.findByText(tag.getText());
        return optionalTag.orElseGet(() -> tagRepository.save(tag));
    }

    @Override
    @Transactional
    public Tag update(Tag tag, Long id) {

        Optional<Tag> optionalTag = tagRepository.findById(id);

        if (optionalTag.isPresent()) {
            tag.setId(id);
            return tagRepository.save(tag);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.NO_TAG_WITH_ID, ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public Tag get(Long id) {

        return tagRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(ExceptionMessages.NO_TAG_WITH_ID, ExceptionCode.NOT_FOUND));
    }

    @Override
    public List<Tag> getAll() {

        return tagRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Optional<Tag> optionalReview = tagRepository.findById(id);

        if (optionalReview.isPresent()) {
            tagRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.NO_TAG_WITH_ID, ExceptionCode.NOT_FOUND);
        }
    }
}
