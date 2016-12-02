package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ibara on 12/2/2016.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
