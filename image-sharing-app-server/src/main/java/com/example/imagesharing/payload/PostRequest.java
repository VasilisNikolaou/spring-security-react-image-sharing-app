package com.example.imagesharing.payload;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;


@Value
public class PostRequest {

    @NotNull(message = "image cannot be null")
    MultipartFile image;

    @NotNull(message = "title cannot be null")
    String title;
}
