package com.example.springsecurity.repository;

import com.example.springsecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.id= :userId and u.isActive = true ")
    User findByUserId(@Param("userId") long userId);

    @Query("select u from User u where u.email= :username and u.isActive=true")
    Optional<User> findByUsername(@Param("username") String username);
}
