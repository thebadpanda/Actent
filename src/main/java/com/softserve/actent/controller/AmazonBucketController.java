package com.softserve.actent.controller;

import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.service.AmazonBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(UrlConstants.API_V1 + UrlConstants.AMAZON_BUCKET_API)
public class AmazonBucketController {

    private AmazonBucketService amazonBucketService;

    @Autowired
    AmazonBucketController(AmazonBucketService amazonBucketService){
        this.amazonBucketService = amazonBucketService;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "image") MultipartFile file) {
        return this.amazonBucketService.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonBucketService.deleteFileFromS3Bucket(fileUrl);
    }
}
