package com.example.imagesharing.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApiError {
    @JsonProperty("status")
    int httpStatus;
    String message;
    List<String> errors;
}
