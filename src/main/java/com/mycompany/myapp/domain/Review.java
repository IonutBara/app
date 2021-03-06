package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ibara on 11/29/2016.
 */
@Entity
@Table(name = "review")
public class Review implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "pros")
    private String pros;

    @Column(name = "contra")
    private String contra;

    @Column(name = "company_rating")
    private Integer rating;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Column(name="is_approved")
    private boolean isApproved = false;

    public Long getId() {
        return id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (!name.equals(review.name)) return false;
        if (description != null ? !description.equals(review.description) : review.description != null) return false;
        if (!pros.equals(review.pros)) return false;
        return contra.equals(review.contra);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (pros != null ? pros.hashCode() : 0);
        result = 31 * result + (contra != null ? contra.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", pros='" + pros + '\'' +
            ", contra='" + contra + '\'' +
            ", rating=" + rating + '\'' +
            //", company=" + company +
            ", isApproved=" + isApproved +
            '}';
    }
}
