package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Authority;
import com.mycompany.myapp.domain.Company;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.security.UserNotActivatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by ibara on 12/6/2016.
 */
@Service
@Transactional
public class CompanyService {

    private final Logger logger = LoggerFactory.getLogger(CompanyService.class);


    /**
     * method which check if user Authority is ADMIN
     * and also if user is company owner
     *
     * @param current
     * @param companyToEdit
     */
    public void checkIfIsAuthorized(User current, Company companyToEdit) throws UserUnauthorizedException {
        Set<Authority> authorities = current.getAuthorities();
        for (Authority auth : authorities) {
            if (auth.getName().equalsIgnoreCase(AuthoritiesConstants.ADMIN)) {
                logger.debug("Admin {} is authorized to edit company {}", current.getEmail(), companyToEdit.getName());
                return;
            }
        }
        Set<Company> userCompanies = current.getCompanies();
        if (!userCompanies.isEmpty()) {
            for (Company company : userCompanies) {
                if (company.getName().equalsIgnoreCase(companyToEdit.getName()))
                    logger.debug("User {} is authorized to edit company {}", current.getEmail(), companyToEdit.getName());
                else
                    throw new UserUnauthorizedException("The user is not authorized to modify company.");
            }
        } else
            throw new UserUnauthorizedException("The user is not authorized to modify company.");
    }


}
