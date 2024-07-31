package com.maathru.backend.External.repository;

import com.maathru.backend.Application.dto.response.ViewBlogDto;
import com.maathru.backend.Domain.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long> {
    // query to get all blogs, with just the first 350 characters of the content column
//    @Query(value = "SELECT title, SUBSTRING(content, 1, 350) as content, author, category, image, location, approval_status, status_reason, keywords FROM blogs", nativeQuery = true)
//    List<Blog> getAllBlogs();

    @Query("SELECT new com.maathru.backend.Application.dto.response.ViewBlogDto(b.title, " +
            "SUBSTRING(b.content, 1, 350), " +
            "b.createdBy, " +
            "b.category, " +
            "b.image, " +
            "b.location, " +
            "b.approvalStatus, " +
            "b.statusReason, " +
            "b.keywords) " +
            "FROM Blog b")
    List<ViewBlogDto> findAllBlogsForDemo();
}