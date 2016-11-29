package com.mycompany.myapp.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ibara on 11/29/2016.
 */
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "zipCode")
    private String zipCode;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!city.equals(address.city)) return false;
        return country.equals(address.country);

    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + id +
            ", city='" + city + '\'' +
            ", zipCode='" + zipCode + '\'' +
            ", country=" + country +
            '}';
    }
}
