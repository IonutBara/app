package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ibara on 12/7/2016.
 */
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("select job from Job job INNER JOIN job.company company WHERE company.id=?1")
    List<Job> listJobsByCompany(Long id);
}
