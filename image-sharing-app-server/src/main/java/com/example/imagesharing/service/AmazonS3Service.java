package com.example.imagesharing.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class AmazonS3Service {

    @Value("${amazonS3.bucketName}")
    private String bucketName;

    private final AmazonS3 s3Client;

    @Async
    public void uploadImage(MultipartFile image, String objectKey) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(image.getContentType());
        objectMetadata.setContentLength(image.getSize());

        PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucketName, objectKey, image.getInputStream(), objectMetadata);

        this.s3Client.putObject(putObjectRequest);
    }

    public byte[] downloadImage(String objectKey) {
        try {
            S3Object imageObject = this.s3Client.getObject(bucketName, objectKey);
            IOUtils.toByteArray(imageObject.getObjectContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
