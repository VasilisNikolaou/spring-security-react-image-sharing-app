package com.example.imagesharing.payload.projections;

import com.example.imagesharing.model.Post;
import com.example.imagesharing.web.PostController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface PostDTO {

    Long getId();

    @JsonProperty("created_by")
    Long getCreatedBy();

    @JsonProperty("created_at")
    Instant getCreatedAt();

    @JsonProperty("title")
    String getPostImageTitle();

    @JsonIgnore
    String getPostImageLink();

    @JsonProperty("total_comments")
    Integer getTotalComments();

    default String getImage_Url() {
        return linkTo(PostController.class).slash("/image/" + getPostImageLink()).toString();
    }

    default String getPost_Url() {
        return linkTo(methodOn(PostController.class).singlePost(getId())).toString();
    }

}
