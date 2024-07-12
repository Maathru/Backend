package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}