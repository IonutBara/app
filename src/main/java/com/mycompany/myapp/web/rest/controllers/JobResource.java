package com.mycompany.myapp.web.rest.controllers;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.config.JHipsterProperties;
import com.mycompany.myapp.domain.Company;
import com.mycompany.myapp.domain.Job;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.exceptions.CompanyNotFoundException;
import com.mycompany.myapp.exceptions.JobNotFoundException;
import com.mycompany.myapp.exceptions.UserUnauthorizedException;
import com.mycompany.myapp.repository.CompanyRepository;
import com.mycompany.myapp.repository.JobRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ibara on 12/7/2016.
 */
@RestController
@RequestMapping("/api")
public class JobResource {

    private final Logger log = LoggerFactory.getLogger(JobResource.class);

    @Inject
    private JobRepository jobRepository;

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private JobService jobService;

    @GetMapping("/jobs")
    @Timed
    public ResponseEntity<?> listAllJobs() {
        List<Job> reviews = jobRepository.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    @Timed
    public ResponseEntity<?> getJob(@RequestParam Long id) throws JobNotFoundException {
        Job job = jobRepository.findOne(id);
        if (job == null)
            throw new JobNotFoundException("");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/jobs/companies/{id}")
    @Timed
    public ResponseEntity<?> listJobsByCompany(@RequestParam Long id) {
        List<Job> jobs = jobRepository.listJobsByCompany(id);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    /**
     * POST  /jobs  : Creates a new job to a specific company
     *
     * @param companyId      the id of company
     * @param jobName
     * @param jobDescription
     * @return the ResponseEntity with status 201 (Created) and with body the new job, or
     * @throws CompanyNotFoundException if the company doesn't exist in database
     */
    @PostMapping("/jobs")
    @Timed
    public ResponseEntity<?> createJob(@RequestParam Long companyId, @RequestParam String jobName,
                                       @RequestParam String jobDescription, @RequestParam String requirements,
                                       @RequestParam String responsibilities, @RequestParam String benefits)
        throws CompanyNotFoundException {
        Company company = companyRepository.findOne(companyId);
        if (company == null)
            throw new CompanyNotFoundException("Company specified to add the job doesn't exist.");
        Job job = new Job();
        job.setCompany(company);
        job.setName(jobName);
        job.setDescription(jobDescription);
        job.setRequirements(requirements);
        job.setResponsibilities(responsibilities);
        job.setBenefits(benefits);
        jobRepository.save(job);
        log.debug("Job {} creating successfully.", job);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/jobs/{id}")
    @Timed
    public ResponseEntity<?> deleteJob(@RequestParam Long id) throws JobNotFoundException, UserUnauthorizedException {

        Job jobToDelete = jobRepository.findOne(id);
        if (jobToDelete == null)
            throw new JobNotFoundException("Job doesn't exist!");
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        if (jobService.checkIfIsAuthorized(userLogged, jobToDelete)) {
            jobRepository.delete(jobToDelete);
            log.debug("Job {} deleting successfully.", jobToDelete);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * PUT  /jobs/save/{id}  : save a new job for a specific user
     *
     * @param id the id of the job
     */
    @PutMapping("/jobs/save/{id}")
    @Timed
    public ResponseEntity<?> saveJob(@RequestParam Long id) {
        User user = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        jobService.saveJob(id, user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * PUT  /jobs/unsave/{id}  : unsave a job for a specific user
     *
     * @param id the id of the job
     */
    @PutMapping("/jobs/unsave/{id}")
    @Timed
    public ResponseEntity<?> unsaveJob(@RequestParam Long id) {
        User user = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        jobService.unsaveJob(id, user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * GET  /jobs/save  : list all saved jobs to a specific user
     */
    @GetMapping("/jobs/save")
    @Timed
    public ResponseEntity<?> listSavedJobs() {
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        return new ResponseEntity<>(jobService.listSavedJobs(userLogged), HttpStatus.OK);
    }
}
