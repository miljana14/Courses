package com.Addressbook.model;


public class ContactResponse {
    private String message;

    public ContactResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
