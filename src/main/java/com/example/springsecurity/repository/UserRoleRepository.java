package com.example.springsecurity.repository;

import com.example.springsecurity.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select r from UserRole r where r.user.id = :userId")
    List<UserRole> findRolesByUserId(@Param("userId") Long userId);
}
