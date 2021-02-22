package com.target.myretail.exceptions;

public class ProductDataNotFoundException extends RuntimeException {

    public ProductDataNotFoundException(Throwable e) {
        super(e);
    }

    public ProductDataNotFoundException(String e) {
        super(e);
    }

}