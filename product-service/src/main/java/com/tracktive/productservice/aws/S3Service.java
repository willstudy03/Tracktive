package com.tracktive.productservice.aws;

import com.tracktive.productservice.exception.FailedToUploadImageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName;
    private final String endpointUrl;

    @Autowired
    public S3Service(S3Client s3Client, @Value("${aws.s3.bucket-name}") String bucketName, @Value("${aws.endpoint-url}")String endpointUrl) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.endpointUrl = endpointUrl;
        createBucketIfNotExists();
    }

    private void createBucketIfNotExists() {
        try {
            s3Client.headBucket(HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
        } catch (NoSuchBucketException e) {
            s3Client.createBucket(CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
        }
    }

    public String uploadFile(MultipartFile image, String productId) {

        String fileName = productId + "_" + image.getOriginalFilename();

        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(image.getContentType())
                    .build();

            s3Client.putObject(request,
                    RequestBody.fromInputStream(image.getInputStream(), image.getSize()));

            return fileName;
        } catch (IOException e) {
            throw new FailedToUploadImageException("Failed to upload file", e);
        }
    }

    public String getFileUrl(String fileName) {
        return String.format("%s/%s/%s",
                endpointUrl,
                bucketName,
                fileName);
    }
}
