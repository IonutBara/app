package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ibara on 12/7/2016.
 */
public interface JobRepository extends JpaRepository<Job, Long> {
}
