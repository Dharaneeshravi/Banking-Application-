package com.dharaneesh.loans.exception;

public class ResourcesNotFoundException extends RuntimeException {


    public ResourcesNotFoundException(String resourceName, String fieldName, String fieldValue) {

        super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
