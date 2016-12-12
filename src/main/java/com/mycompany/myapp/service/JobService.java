package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Authority;
import com.mycompany.myapp.domain.Company;
import com.mycompany.myapp.domain.Job;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.exceptions.UserUnauthorizedException;
import com.mycompany.myapp.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by ibara on 12/12/2016.
 */
@Service
public class JobService {

    private final Logger logger = LoggerFactory.getLogger(JobService.class);


    public boolean checkIfIsAuthorized(User userLogged, Job job) throws UserUnauthorizedException {
        Set<Authority> authorities = userLogged.getAuthorities();
        if (!authorities.isEmpty()) {
            for (Authority authority : authorities) {
                if (AuthoritiesConstants.ADMIN.equalsIgnoreCase(authority.getName())) {
                    logger.debug("Admin is authorized to make changes to every job");
                    return true;
                } else if (AuthoritiesConstants.USER.equalsIgnoreCase(authority.getName())) {
                    Set<Company> companies = userLogged.getCompanies();
                    if (!companies.isEmpty()) {
                        for (Company company : companies) {
                            if (company.getId() == job.getCompany().getId()) {
                                logger.debug("User {} is authorized to make changes to this job", userLogged.getEmail());
                                return true;
                            }
                        }
                    }
                }
            }
        } else {
            throw new UserUnauthorizedException("The user is not authorized to make changes to this job.");
        }
        return false;
    }
}
