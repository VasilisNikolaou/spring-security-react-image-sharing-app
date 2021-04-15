package com.example.imagesharing.payload.projections;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface PostDTO {

    Long getCreatedBy();

    Instant getCreatedAt();

    Instant getModifiedAt();

    String getPostImageTitle();

    String getPostImageLink();

    @Value("#{target.postComments.size()}")
    Integer getTotalComments();

}
