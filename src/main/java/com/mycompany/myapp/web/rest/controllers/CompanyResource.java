package com.mycompany.myapp.web.rest.controllers;

/**
 * Created by ibara on 12/2/2016.
 */

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Company;
//import com.mycompany.myapp.domain.CompanyOwners;
import com.mycompany.myapp.domain.CompanyOwners;
import com.mycompany.myapp.domain.User;
//import com.mycompany.myapp.repository.CompanyOwnersRepository;
import com.mycompany.myapp.repository.CompanyOwnersRepository;
import com.mycompany.myapp.repository.CompanyRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.dto.CompanyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing the company process.
 */
@RestController
@RequestMapping("/api")
public class CompanyResource {

    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);


    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private CompanyOwnersRepository companyOwnersRepository;


    /**
     * GET  /companies : list all existent companies.
     *
     * @return the ResponseEntity with status 200 (OK) and all companies in body, or status 500 (Internal Server Error) if the list couldn't be returned
     */
    @GetMapping("/companies")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<Company>> listAll() {
        List<Company> allCompanies = companyRepository.findAll();
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }

    @PostMapping("/companies")
    @Timed
    public ResponseEntity<?> createCompany(@RequestBody String companyName) {
        User userLogged = userRepository.getUserByLogin("ibara@lenovo.com");
        List<User> owners = new ArrayList<>();
        owners.add(userLogged);
        Company newCompany = new Company();
        newCompany.setName(companyName);
        //newCompany.setDescription(company.getDescription());
        newCompany.setRating(0);
        //newCompany.setOwners(owners);
        companyRepository.save(newCompany);

        CompanyOwners companyOwners = new CompanyOwners();
        companyOwners.setCompany(newCompany);
        companyOwners.setUser(userLogged);
        companyOwnersRepository.save(companyOwners);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
