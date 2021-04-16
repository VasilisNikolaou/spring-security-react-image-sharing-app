package com.example.imagesharing.service;

import com.example.imagesharing.exceptionhandling.exceptions.ImageEmptyException;
import com.example.imagesharing.exceptionhandling.exceptions.ImageMIMETypeException;
import com.example.imagesharing.model.Post;
import com.example.imagesharing.payload.PostRequest;
import com.example.imagesharing.payload.projections.PagedPostDTO;
import com.example.imagesharing.payload.projections.PostDTO;
import com.example.imagesharing.repository.PostRepository;
import com.example.imagesharing.web.PostController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AmazonS3Service amazonS3Service;

    public Post uploadImageAndSavePost(PostRequest postRequest) {
        MultipartFile image = postRequest.getImage();
        String title = postRequest.getTitle();

        String key = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        try {

            if (!image.isEmpty()) {
                this.checkMIMEType(image.getContentType());
                this.amazonS3Service.uploadImage(image, key);
            } else {
                throw new ImageEmptyException();
            }

        } catch (IOException ioe) {
            //Don't do this in production
            ioe.printStackTrace();
        }

        return this.postRepository.save(new Post(title, key));
    }

    public PagedPostDTO pageablePosts(Pageable pageable) {
        Page<Long> pagedIdsOfPosts = this.postRepository.fetchIdsOfPosts(pageable);

        if (pagedIdsOfPosts.getContent().size() > 0) {
            List<PostDTO> postDTOS =
                    this.postRepository.fetchPostsWithoutPostComments(pagedIdsOfPosts.getContent());
            PagedPostDTO pagedPostDTO = new PagedPostDTO(postDTOS);

            if (!pagedIdsOfPosts.isLast()) {
                pagedPostDTO.setNextLink(
                        linkTo(methodOn(PostController.class)
                                .pagedPosts(pagedIdsOfPosts.getNumber() + 1,
                                        pagedIdsOfPosts.getSize())).toString());
            }

            pagedPostDTO.setLastLink(
                    linkTo(methodOn(PostController.class)
                            .pagedPosts(pagedIdsOfPosts.getTotalPages() - 1,
                                    pagedIdsOfPosts.getSize())).toString());

            pagedPostDTO.setLast(pagedIdsOfPosts.isLast());

            return pagedPostDTO;
        }

        return new PagedPostDTO(List.of());
    }

    public PostDTO findPostById(Long id) {
        return this.postRepository.fetchPostById(id);
    }

    public byte[] downloadImage(String objectKey) {
        return this.amazonS3Service.downloadImage(objectKey);
    }


    private void checkMIMEType(String contentType) {
        Arrays.stream(new String[]{MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
                .filter(mimetype -> mimetype.equals(contentType))
                .findAny().orElseThrow(() -> new ImageMIMETypeException(contentType));
    }

}
