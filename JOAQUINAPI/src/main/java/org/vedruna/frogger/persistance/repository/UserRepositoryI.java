package org.vedruna.frogger.persistance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.vedruna.frogger.persistance.model.User;


import java.util.Optional;


@Repository
public interface UserRepositoryI extends JpaRepository<User, Integer> {

    Optional<Object> findByUsername(String name);

    Page<User> findByUsernameStartingWith(String name, Pageable pageable);

}