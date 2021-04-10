package com.example.imagesharing.exceptionhandling.exceptions;

public class ImageEmptyException extends RuntimeException{

    public ImageEmptyException () {
        super("image cannot be empty");
    }
}
