package com.target.myretail.exceptions;

public class ProductServicesException extends RuntimeException {

    public ProductServicesException(Throwable e) {
        super(e);
    }

    public ProductServicesException(String e) {
        super(e);
    }
}
