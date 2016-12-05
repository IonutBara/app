package com.mycompany.myapp.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ibara on 12/2/2016.
 */
@Entity
@Table(name = "company_owners")
public class CompanyOwners implements Serializable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

        CompanyOwners that = (CompanyOwners) o;

        if (!user.equals(that.user)) return false;
        return company.equals(that.company);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + company.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CompanyOwners{" +
            "user_id=" + user +
            ", company_id=" + company +
            '}';
    }
}
