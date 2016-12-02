package com.mycompany.myapp.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created by ibara on 11/29/2016.
 */
@Entity
@Table(name = "review")
public class Review {

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

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Company company;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (!name.equals(review.name)) return false;
        if (pros != null ? !pros.equals(review.pros) : review.pros != null) return false;
        if (contra != null ? !contra.equals(review.contra) : review.contra != null) return false;
        return company.equals(review.company);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (pros != null ? pros.hashCode() : 0);
        result = 31 * result + (contra != null ? contra.hashCode() : 0);
        result = 31 * result + company.hashCode();
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
            ", company=" + company +
            '}';
    }
}
