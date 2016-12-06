package com.mycompany.myapp.web.rest.controllers;

/**
 * Created by ibara on 12/2/2016.
 */

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Company;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.exceptions.CompanyNotFoundException;
import com.mycompany.myapp.repository.CompanyRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.CompanyService;
import com.mycompany.myapp.exceptions.UserUnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.*;

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
    private CompanyService companyService;


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
    public ResponseEntity<?> createCompany(@RequestParam String companyName, String description) {
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        Set<User> owners = new HashSet<>();
        log.debug("User userLogged {} ", userLogged);
        owners.add(userLogged);
        Company newCompany = new Company();
        newCompany.setName(companyName);
        newCompany.setDescription(description);
        newCompany.setRating(0);
        newCompany.setUsers(owners);
        companyRepository.save(newCompany);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/companies/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    public ResponseEntity<?> deleteCompany(@RequestParam Long id) throws CompanyNotFoundException, UserUnauthorizedException {
        Company toDelete = companyRepository.findOne(id);
        if (toDelete == null)
            throw new CompanyNotFoundException("This Company doesn't exist in portal.");
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        companyService.checkIfIsAuthorized(userLogged, toDelete);
        companyRepository.delete(toDelete);
        log.debug("Deleted Company: {}", toDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/companies/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    public ResponseEntity<?> getCompany(@RequestParam Long id) throws CompanyNotFoundException, UserUnauthorizedException {
        Company company = companyRepository.findOne(id);
        if (company == null)
            throw new CompanyNotFoundException("This Company doesn't exist in portal.");
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        companyService.checkIfIsAuthorized(userLogged, company);
        log.debug("Returned Company: {}", company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
