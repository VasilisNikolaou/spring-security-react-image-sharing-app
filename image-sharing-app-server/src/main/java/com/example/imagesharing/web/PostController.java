package com.example.imagesharing.web;

import com.example.imagesharing.payload.ApiError;
import com.example.imagesharing.payload.PostRequest;
import com.example.imagesharing.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPost(@Valid @ModelAttribute PostRequest postRequest, Errors errors) {
        if (errors.hasErrors()) {
            List<String> fieldErrors = errors.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            final ApiError apiError =
                    new ApiError(HttpStatus.BAD_REQUEST.value(), "error input fields", fieldErrors);

            return ResponseEntity.badRequest().body(apiError);
        }

        return ResponseEntity.ok(postService.uploadImageAndSavePost(postRequest));
    }
}
