package com.example.imagesharing.payload.projections;

import com.example.imagesharing.web.PostController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface PostDTO {

    Long getId();

    @JsonProperty("created_by")
    Long getCreatedBy();

    @JsonProperty("created_at")
    Instant getCreatedAt();

    @JsonProperty("updated_at")
    Instant getModifiedAt();

    @JsonProperty("title")
    String getPostImageTitle();

    @JsonIgnore
    String getPostImageLink();

    @JsonProperty("total_comments")
    Integer getTotalComments();

    @JsonProperty("image_url")
    default String getImageUrl() {
        return linkTo(PostController.class).slash("/image/" + getPostImageLink()).toString();
    }

    @JsonProperty("post_url")
    default String getPostUrl() {
        return linkTo(methodOn(PostController.class).singlePost(getId())).toString();
    }

}
