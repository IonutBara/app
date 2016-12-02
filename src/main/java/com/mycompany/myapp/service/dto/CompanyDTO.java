package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Address;
import com.mycompany.myapp.domain.Review;
import com.mycompany.myapp.domain.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ibara on 11/29/2016.
 */
public class CompanyDTO {

    private Long id;

    private String name;

    private String description;

    private Address address;

    private double rating;

    private Set<Review> reviews = new HashSet<>();

    private List<User> owners = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public CompanyDTO(){}

    public CompanyDTO(String name, String description, Address address, double rating, Set<Review> reviews, List<User> owners) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.reviews = reviews;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", address=" + address +
            ", rating=" + rating +
            ", reviews=" + reviews +
            ", owners=" + owners +
            '}';
    }
}
