package com.example.imagesharing.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

    private final String awsAccessKey = "";
    private final String awsSecretKey = "";
    private final String sessionToken = "";
    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicSessionCredentials(awsAccessKey, awsSecretKey, sessionToken)))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

}
