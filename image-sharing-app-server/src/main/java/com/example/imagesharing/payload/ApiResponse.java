package com.example.imagesharing.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class ApiResponse {
    private final String message;
}
