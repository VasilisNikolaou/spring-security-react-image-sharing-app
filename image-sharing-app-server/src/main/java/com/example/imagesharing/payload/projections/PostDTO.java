package com.example.imagesharing.payload.projections;

import com.example.imagesharing.model.Post;
import com.example.imagesharing.web.PostController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface PostDTO {

    Long getId();

    Long getCreatedBy();

    Instant getCreatedAt();

    Instant getModifiedAt();

    String getPostImageTitle();

    @JsonIgnore
    String getPostImageLink();

    @Value("#{target.postComments.size()}")
    Integer getTotalComments();

    default String getImage_Url() {
        return linkTo(PostController.class).slash("/image/"+getPostImageLink()).toString();
    }

    default String getPost_Url() {
        return linkTo(methodOn(PostController.class).post(getId())).toString();
    }

}
