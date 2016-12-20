package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Company;
import com.mycompany.myapp.domain.Review;
import com.mycompany.myapp.domain.util.HelperClass;
import com.mycompany.myapp.repository.CompanyRepository;
import com.mycompany.myapp.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ibara on 12/14/2016.
 */
@Service
@EnableAsync
public class ReviewService {

    private final Logger log = LoggerFactory.getLogger(ReviewService.class);

    @Inject
    private CompanyRepository companyRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Service for update rating for company
     * <p>
     * We use the @Async annotation to update company rating asynchronously
     * when a new review was approve by ADMIN
     * </p>
     */
    @Async
    public void updateRating(Review review) {
        Company company = review.getCompany();
        double companyRating = 0.0;
        while (!review.isApproved()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Review> approvedReviews = reviewRepository.listApprovedReviews(company);
        if (!approvedReviews.isEmpty()) {
            double ratingCount = 0;
            for (Review rev : approvedReviews) {
                ratingCount += rev.getRating();
            }
            companyRating = ratingCount / approvedReviews.size();
            log.debug("companyRating: {}", companyRating);
        }
        company.setRating(HelperClass.format2Decimal(companyRating));
        companyRepository.save(company);
    }


}
