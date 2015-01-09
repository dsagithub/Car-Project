package com.example.prototype1;

/**
 * Created by yifeige on 28/12/14.
 */
public class AppException extends Exception {
    public AppException() {
        super();
    }

    public AppException(String detailMessage)
    {
        super(detailMessage);
    }
}