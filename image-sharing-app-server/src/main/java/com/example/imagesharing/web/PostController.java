package com.example.imagesharing.web;

import com.example.imagesharing.model.Post;
import com.example.imagesharing.payload.ApiError;
import com.example.imagesharing.payload.PostRequest;
import com.example.imagesharing.payload.projections.PagedPostDTO;
import com.example.imagesharing.payload.projections.PostDTO;
import com.example.imagesharing.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
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
        Post post = this.postService.uploadImageAndSavePost(postRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).body(post);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedPostDTO> pagedPosts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.
                ok(this.postService.pageablePosts(PageRequest.of(page, size)));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> post(@PathVariable Long id) {
        return ResponseEntity.ok(this.postService.findPostById(id));
    }

    @GetMapping("/image/{objectKey}")
    public byte[] downloadImage(@PathVariable String objectKey) {
        return this.postService.downloadImage(objectKey);
    }

}
