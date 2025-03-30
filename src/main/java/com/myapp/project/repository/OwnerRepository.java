package com.myapp.project.repository;

import com.myapp.project.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByEmail(String email);
}
