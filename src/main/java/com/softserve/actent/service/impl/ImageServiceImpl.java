package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.IncorrectStringException;
import com.softserve.actent.model.entity.Image;
import com.softserve.actent.repository.ImageRepository;
import com.softserve.actent.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image add(Image image) {

        Optional<Image> optionalImage = imageRepository.findByHash(image.getHash());
        return optionalImage.orElseGet(() -> imageRepository.save(image));
    }

    @Override
    public Image get(Long imageId) {

        Optional<Image> optionalImage = imageRepository.findById(imageId);

        return optionalImage.orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NO_IMAGE_WITH_ID, ExceptionCode.NOT_FOUND));
    }

    @Override
    public Image getImageByFilePath(String filePath) {

        Optional<Image> optionalImage = imageRepository.findByFilePath(filePath);

        return optionalImage.orElseThrow(()
                -> new ResourceNotFoundException(ExceptionMessages.NO_IMAGE_WITH_PATH, ExceptionCode.NOT_FOUND));
    }

    @Override
    public Image getImageByHash(String hash) {

        if (hash.length() != 64) {
            throw new IncorrectStringException("SHA256 hash must be exactly 64 symbols in length.",
                    ExceptionCode.INCORRECT_STRING);
        }

        Optional<Image> optionalImage = imageRepository.findByHash(hash);

        return optionalImage.orElseThrow(()
                -> new ResourceNotFoundException(ExceptionMessages.NO_IMAGE_WITH_HASH, ExceptionCode.NOT_FOUND));
    }

    @Override
    public List<Image> getAll() {

        return imageRepository.findAll();
    }

    @Override
    public Image update(Image image, Long imageId) {

        Optional<Image> optionalImage = imageRepository.findById(imageId);

        if (optionalImage.isPresent()) {
            image.setId(imageId);
            return imageRepository.save(image);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.NO_IMAGE_WITH_ID, ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public void delete(Long imageId) {

        Optional<Image> optionalImage = imageRepository.findById(imageId);

        if(optionalImage.isPresent()) {
            imageRepository.deleteById(imageId);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.NO_IMAGE_WITH_ID, ExceptionCode.NOT_FOUND);
        }
    }
}
