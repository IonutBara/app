package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ibara on 11/29/2016.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
