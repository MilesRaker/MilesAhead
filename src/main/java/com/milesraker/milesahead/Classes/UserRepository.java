package com.milesraker.milesahead.Classes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findFirstByUsername(String username);
    User findById(long id);

    List<User> findAll();
}
