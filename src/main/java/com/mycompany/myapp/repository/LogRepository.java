package com.mycompany.myapp.repository;


import com.mycompany.myapp.domain.Logs;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ibara on 3/6/2017.
 */
public interface LogRepository extends CrudRepository<Logs, Long> {
}
