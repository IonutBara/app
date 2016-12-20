package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Company;
import com.mycompany.myapp.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ibara on 12/2/2016.
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select review from Review review INNER JOIN review.company company WHERE company.id=?1")
    List<Review> findReviewsByCompany(Long companyId);

    @Query("select review from Review review INNER JOIN review.company company WHERE company=?1 and review.isApproved=true")
    List<Review> listApprovedReviews(Company company);
}
