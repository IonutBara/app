package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Company;

/**
 * Created by ibara on 11/29/2016.
 */
public class ReviewDTO {

    private Long id;

    private String name;

    private String description;

    private String pros;

    private String contra;

    private Company company;

    private Integer rating;

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

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ReviewDTO(String name, String description, String pros, String contra, Company company, Integer rating) {
        this.name = name;
        this.description = description;
        this.pros = pros;
        this.contra = contra;
        this.company = company;
        this.rating = rating;
    }

    public ReviewDTO() {
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", pros='" + pros + '\'' +
            ", contra='" + contra + '\'' +
            ", company=" + company +
            ", rating=" + rating +
            '}';
    }
}
