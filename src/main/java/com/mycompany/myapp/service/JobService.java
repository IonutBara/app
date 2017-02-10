package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Authority;
import com.mycompany.myapp.domain.Company;
import com.mycompany.myapp.domain.Job;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.exceptions.UserUnauthorizedException;
import com.mycompany.myapp.repository.JobRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by ibara on 12/12/2016.
 */
@Service
public class JobService {

    private final Logger logger = LoggerFactory.getLogger(JobService.class);

    @Inject
    private JobRepository jobRepository;

    @Inject
    private UserRepository userRepository;

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

    public void saveJob(Long jobId, User user) {
        StringBuilder sb;
        if (user.getSavedJobs() == null ||
            (user.getSavedJobs().equalsIgnoreCase(""))) {
            sb = new StringBuilder("");
            sb.append(jobId);
        } else {
            sb = new StringBuilder(user.getSavedJobs());
            sb.append(",").append(jobId);
        }
        user.setSavedJobs(sb.toString());
        userRepository.save(user);
    }

    public void unsaveJob(Long jobId, User user) {
        StringBuilder sb = new StringBuilder("");
        String savedJobs = user.getSavedJobs();
        String[] jobsAsArray = savedJobs.split(",");
        for (int i = 0; i < jobsAsArray.length; i++) {
            if (!jobsAsArray[i].equalsIgnoreCase("" + jobId)) {
                sb.append(jobsAsArray[i]).append(",");
            }
        }
        if (sb.length() > 0) {
            user.setSavedJobs(sb.toString().substring(0, sb.length() - 1));
        } else {
            user.setSavedJobs("");
        }
        userRepository.save(user);
    }

    public List<Job> listSavedJobs(User user) {
        if (user == null) {
            logger.error("User logged should not be null");
            throw new NullPointerException("User logged should not be null");
        }
        List<Job> jobs = new ArrayList<>();
        if (user.getSavedJobs() == null) {
            logger.debug("This user does't have any saved jobs");
            return Collections.emptyList();
        }
        String[] allJobsAsArray = user.getSavedJobs().split(",");
        for (int i = 0; i < allJobsAsArray.length; i++) {
            try {
                Job job = jobRepository.findOne(Long.parseLong(allJobsAsArray[i]));
                if (job != null) {
                    jobs.add(job);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return jobs;
    }

    @Transactional
    void updateListSavedJobs(User user) {
        StringBuilder sb = new StringBuilder("");
        List<Job> allJobs = listSavedJobs(user);
        if (allJobs.isEmpty()) {
            return;
        }
        for (Job job : allJobs) {
            Job jobFromDb = jobRepository.findOne(job.getId());
            if (jobFromDb != null) {
                sb.append(job.getId()).append(",");
            }
        }
        if (sb.length() > 0) {
            user.setSavedJobs(sb.toString().substring(0, sb.length() - 1));
        } else {
            user.setSavedJobs("");
        }
    }
}
