package com.example.domain;

import java.io.Serializable;

public class VoteResponse implements Serializable {

    private String errorDetails;

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}
