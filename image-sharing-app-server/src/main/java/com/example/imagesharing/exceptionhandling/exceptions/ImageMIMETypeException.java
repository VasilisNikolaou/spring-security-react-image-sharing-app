package com.example.imagesharing.exceptionhandling.exceptions;

public class ImageMIMETypeException extends RuntimeException{

    public ImageMIMETypeException(String contentType) {
       super("wrong MIME format " + contentType);
    }
}
