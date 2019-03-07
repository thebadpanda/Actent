package com.softserve.actent.service.impl;

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

        return imageRepository.save(image);
    }

    @Override
    public Image get(Long imageId) {

        Optional<Image> optionalImage = imageRepository.findById(imageId);

        return optionalImage.orElse(null);
    }

    @Override
    public Image getImageByFilePath(String filePath) {

        Optional<Image> optionalImage = imageRepository.findByFilePath(filePath);

        return optionalImage.orElse(null);
    }

    @Override
    public Image getImageByHash(String hash) {

        Optional<Image> optionalImage = imageRepository.findByHash(hash);

        return optionalImage.orElse(null);
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
            return null;
        }
    }

    @Override
    public void delete(Long imageId) {

        Optional<Image> optionalImage = imageRepository.findById(imageId);

        if(optionalImage.isPresent()) {
            imageRepository.deleteById(imageId);
        }

        // TODO: else throw exception or so
    }
}
