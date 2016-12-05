package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Address;
import com.mycompany.myapp.domain.Review;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ibara on 11/29/2016.
 */
public class CompanyDTO {

    private Long id;

    private String name;

    private String description;

    //private Address address;

    private double rating;

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

/*    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }*/

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public CompanyDTO(){}

    public CompanyDTO(String name, String description, Address address, double rating, Set<Review> reviews) {
        this.name = name;
        this.description = description;
        //this.address = address;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
           // ", address=" + address +
            ", rating=" + rating +
            '}';
    }
}
