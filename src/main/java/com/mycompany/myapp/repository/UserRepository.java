package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.User;

import java.time.ZonedDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    @Query(value = "select distinct user from User user left join fetch user.authorities",
        countQuery = "select count(user) from User user")
    Page<User> findAllWithAuthorities(Pageable pageable);

    //@Query(value = "select user from User user WHERE user.login=?1")
    @Query(value="from User as u where u.login = ?1")
    User getUserByLogin(String login);

    @Override
    void delete(User t);

/*    @Query(value = "select user from User user INNER JOIN user.address address WHERE address.country.name=?1")
    List<User> findAllByCountry(String countryName);*/

/*  SELECT c
    FROM Customer c INNER JOIN c.orders o
    WHERE c.status = 1*/
}
