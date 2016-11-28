package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ibara on 11/28/2016.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
}
