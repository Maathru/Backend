package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Parent;
import com.maathru.backend.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Parent findByUser(User currentUser);
}