package com.example.imagesharing.payload.projections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PagedPostDTO {

    @JsonProperty("posts")
    private List<PostDTO> postDTOS;

    @JsonProperty("next_url")
    private String nextLink;

    @JsonProperty("last_url")
    private String lastLink;

    @JsonProperty("isLast")
    private boolean isLast;

    public PagedPostDTO(List<PostDTO> postDTOS) {
        this.postDTOS = postDTOS;
    }
}
