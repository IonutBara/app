package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.config.Constants;

import com.mycompany.myapp.domain.*;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    private String langKey;

    private Set<String> authorities;

    private Address address;

    private Set<String> companies;

    private String savedJobs;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getLogin(), user.getFirstName(), user.getLastName(),
            user.getEmail(), user.getActivated(), user.getLangKey(),
            user.getAuthorities().stream().map(Authority::getName)
                .collect(Collectors.toSet()),//, user.getAddress()
            user.getCompanies().stream().map(Company::getName)
                .collect(Collectors.toSet()), user.getSavedJobs()
            );
    }

    public UserDTO(String login, String firstName, String lastName,
                   String email, boolean activated, String langKey,
                   Set<String> authorities,  //,Address address//,
                   Set<String> companies, String savedJobs
    ) {

        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
        this.address = address;
        this.companies = companies;
        this.savedJobs = savedJobs;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public Address getAddress() {
        return address;
    }

    public Set<String> getCompanies() {
        return companies;
    }

    public String getSavedJobs() {
        return savedJobs;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", activated=" + activated +
            ", langKey='" + langKey + '\'' +
            ", authorities=" + authorities +
            //", address=" + address +
            ", companies=" + companies +
            ", savedJobs=" + savedJobs +
            "}";
    }
}
