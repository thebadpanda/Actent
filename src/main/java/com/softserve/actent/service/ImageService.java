package com.softserve.actent.service;

import com.softserve.actent.model.entity.Image;

import java.util.List;

public interface ImageService {

    Image addImage(Image image);

    Image getImageById(Long imageId);

    Image getImageByFilePath(String filePath);

    Image getImageByHash(String hash);

    List<Image> getAllImages();

    Image updateImageById(Image image, Long imageId);

    void deleteImageById(Long imageId);
}
