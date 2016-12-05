package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CompanyOwners;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ibara on 12/2/2016.
 */
public interface CompanyOwnersRepository extends JpaRepository<CompanyOwners, Long> {
}
