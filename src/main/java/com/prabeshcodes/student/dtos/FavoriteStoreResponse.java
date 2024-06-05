package com.prabeshcodes.student.dtos;

public class FavoriteStoreResponse {

    private String name;
    private String contactDetails;

    // Default constructor
    public FavoriteStoreResponse() {
    }

    // Parameterized constructor
    public FavoriteStoreResponse(String name, String contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    @Override
    public String toString() {
        return "FavouriteStoreResponse{" +
                "name='" + name + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                '}';
    }
}
