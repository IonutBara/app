package com.mycompany.myapp.web.rest.controllers;

/**
 * Created by ibara on 12/6/2016.
 */

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Company;
import com.mycompany.myapp.domain.Review;
import com.mycompany.myapp.exceptions.CompanyNotFoundException;
import com.mycompany.myapp.exceptions.ReviewNotFoundException;
import com.mycompany.myapp.exceptions.UserUnauthorizedException;
import com.mycompany.myapp.repository.CompanyRepository;
import com.mycompany.myapp.repository.ReviewRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.security.SecurityUtils;
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
public class ReviewController {

    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    @Inject
    private ReviewRepository reviewRepository;

    @Inject
    private CompanyRepository companyRepository;

    @GetMapping("/reviews")
    @Timed
    public ResponseEntity<?> listReviewsForCompany(@RequestParam Long id) {
        String authority = SecurityUtils.getCurrentUserLogin();
        List<Review> reviews = reviewRepository.findReviewsByCompany(id);
        if (AuthoritiesConstants.USER.equalsIgnoreCase(authority)) {
            List<Review> reviewApproved = new ArrayList<>();
            for (Review review : reviews) {
                if (review.isApproved()) {
                    reviewApproved.add(review);
                }
            }
            log.debug("Listing approved reviews for company with id {}", id);
            return new ResponseEntity<>(reviewApproved, HttpStatus.OK);
        }
        log.debug("Listing all reviews for company with id {}", id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    @Timed
    public ResponseEntity<?> getReviewById(@RequestParam Long id) throws ReviewNotFoundException, UserUnauthorizedException {
        Boolean authority = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN);
        Review review = reviewRepository.findOne(id);
        if (review == null)
            throw new ReviewNotFoundException("The review doesn't exist.");
        if (!review.isApproved() && !authority)
            throw new UserUnauthorizedException("Only ADMIN is authorized to see an unapproved review.");
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    @Timed
    public ResponseEntity<?> createReview(@RequestParam String name, String description,
                                          @RequestParam String pro, @RequestParam String contra,
                                          @RequestParam Long companyId) throws CompanyNotFoundException {
        Company cmp = companyRepository.findOne(companyId);
        if (cmp == null)
            throw new CompanyNotFoundException("You can not add a review at a company that does not exist!");
        Review review = new Review();
        review.setName(name);
        review.setDescription(description);
        review.setPros(pro);
        review.setContra(contra);
        review.setCompany(cmp);
        review.setRating(3);
        reviewRepository.save(review);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/reviews/{id}")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> approveReview(@RequestParam Long id) throws ReviewNotFoundException {
        Review reviewToApprove = reviewRepository.findOne(id);
        if (reviewToApprove == null)
            throw new ReviewNotFoundException("The review doesn't exist.");
        reviewToApprove.setApproved(true);
        reviewRepository.save(reviewToApprove);
        return new ResponseEntity<>(reviewToApprove, HttpStatus.OK);
    }


}
