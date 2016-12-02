package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ibara on 12/2/2016.
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
