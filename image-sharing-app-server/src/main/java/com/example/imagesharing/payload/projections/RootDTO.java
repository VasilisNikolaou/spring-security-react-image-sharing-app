package com.example.imagesharing.payload.projections;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class RootDTO {

    @JsonProperty("create_post_url")
    String createPostUrl;

    @JsonProperty("posts")
    String posts;
}
