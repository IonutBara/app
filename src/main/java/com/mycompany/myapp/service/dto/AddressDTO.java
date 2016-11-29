package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Country;

/**
 * Created by ibara on 11/29/2016.
 */
public class AddressDTO {

    private Long id;

    private String city;

    private String zipCode;

    private Country country;

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipcode) {
        this.zipCode = zipcode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AddressDTO() {
    }

    public AddressDTO(String city, String zipCode, Country country) {
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }


    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + id +
            ", city='" + city + '\'' +
            ", zipCode='" + zipCode + '\'' +
            ", country=" + country +
            '}';
    }
}
