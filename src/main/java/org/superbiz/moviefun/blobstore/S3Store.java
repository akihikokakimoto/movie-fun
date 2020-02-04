package org.superbiz.moviefun.blobstore;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Optional;

public class S3Store implements BlobStore{

    private AmazonS3Client s3Client;
    private String photoStorageBucket;

    public S3Store(AmazonS3Client s3Client, String photoStorageBucket) {
        this.s3Client = s3Client;
        this.photoStorageBucket = photoStorageBucket;
    }

    @Override
    public void put(Blob blob) throws IOException {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType(blob.contentType);
        meta.setContentLength(blob.contentLength);

        s3Client.putObject(photoStorageBucket,blob.name, blob.inputStream, meta);
    }

    @Override
    public Optional<Blob> get(String name) throws IOException {
        S3Object s3 = s3Client.getObject(photoStorageBucket, name);

        if(s3 != null) {
            return Optional.of(new Blob(name, s3.getObjectContent(), s3.getObjectMetadata().getContentType(), s3.getObjectMetadata().getContentLength()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteAll() {
    }
}
