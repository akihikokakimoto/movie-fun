package org.superbiz.moviefun.blobstore;

import java.io.InputStream;

public class Blob {
    public final String name;
    public final InputStream inputStream;
    public final String contentType;
    public final long contentLength;

    public Blob(String name, InputStream inputStream, String contentType, long contentLength) {
        this.name = name;
        this.inputStream = inputStream;
        this.contentType = contentType;
        this.contentLength = contentLength;
    }
}