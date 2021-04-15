package com.example.imagesharing.service;

import com.example.imagesharing.exceptionhandling.exceptions.ImageEmptyException;
import com.example.imagesharing.exceptionhandling.exceptions.ImageMIMETypeException;
import com.example.imagesharing.model.Post;
import com.example.imagesharing.payload.PostRequest;
import com.example.imagesharing.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UploadService uploadService;

    public Post uploadImageAndSavePost(PostRequest postRequest) {
        MultipartFile image = postRequest.getImage();
        String title = postRequest.getTitle();

        String key = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        try {

            if (!image.isEmpty()) {
                this.checkMIMEType(image.getContentType());
                this.uploadService.uploadImage(image, key);
            } else {
                throw new ImageEmptyException();
            }

        } catch (IOException ioe) {
            //Don't do this in production
            ioe.printStackTrace();
        }

        return this.postRepository.save(new Post(title, key));
    }

    private void checkMIMEType(String contentType) {
        Arrays.stream(new String[]{MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
                .filter(mimetype -> mimetype.equals(contentType))
                .findAny().orElseThrow(() -> new ImageMIMETypeException(contentType));
    }

}
