package com.example.imagesharing.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.imagesharing.exceptionhandling.exceptions.ImageEmptyException;
import com.example.imagesharing.exceptionhandling.exceptions.ImageMIMETypeException;
import com.example.imagesharing.payload.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${amazonS3.bucketName}")
    private String bucketName;
    private final AmazonS3 s3Client;

    public void uploadImageAndSavePost(PostRequest postRequest) {
        try {
            MultipartFile image = postRequest.getImage();
            String title = postRequest.getTitle();

            if (!image.isEmpty()) {
                checkMIMEType(image.getContentType());

                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType(image.getContentType());
                objectMetadata.setContentLength(image.getSize());

                var key = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

                PutObjectRequest putObjectRequest =
                        new PutObjectRequest(bucketName, key, image.getInputStream(), objectMetadata);

                s3Client.putObject(putObjectRequest);

                //TODO: save title, and bucket+key to database

            } else {
                throw new ImageEmptyException();
            }
        } catch (IOException ioe) {
            //Don't do this in production
            ioe.printStackTrace();
        }
    }


    private void checkMIMEType(String contentType) {
        Arrays.stream(new String[]{MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
                .filter(mimetype -> mimetype.equals(contentType))
                .findAny().orElseThrow(() -> new ImageMIMETypeException(contentType));

    }

}
