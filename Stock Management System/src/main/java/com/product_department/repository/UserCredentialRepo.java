package com.product_department.repository;

import com.product_department.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepo extends JpaRepository<UserCredential , Integer> {

    Optional<UserCredential> findByName(String UserName);
}
