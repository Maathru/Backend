package com.maathru.backend.External.repository;

import com.maathru.backend.Application.dto.response.ViewBlogDto;
import com.maathru.backend.Domain.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("SELECT new com.maathru.backend.Application.dto.response.ViewBlogDto( b.title, " +
            "b.blogId, " +
            "SUBSTRING(b.content, 1, 350), " +
            "b.createdBy.userId, " +
            "b.category, " +
            "b.image, " +
            "b.location, " +
            "b.approvalStatus, " +
            "b.statusReason, " +
            "b.keywords ) " +
            "FROM Blog b")
    List<ViewBlogDto> findAllBlogsForDemo();

    @Query("SELECT new com.maathru.backend.Application.dto.response.ViewBlogDto(b.title, " +
            "b.blogId, " +
            "b.content, " +
            "b.createdBy.userId, " +
            "b.category, " +
            "b.image, " +
            "b.location, " +
            "b.approvalStatus, " +
            "b.statusReason, " +
            "b.keywords ) " +
            "FROM Blog b " +
            "WHERE b.blogId=:articleId")
    Optional<ViewBlogDto> findArticle(@Param("articleId") Long articleId);

    @Query("SELECT new com.maathru.backend.Application.dto.response.ViewBlogDto( b.title, " +
            "b.blogId, " +
            "SUBSTRING(b.content, 1, 350), " +
            "b.createdBy.userId, " +
            "b.category, " +
            "b.image, " +
            "b.location, " +
            "b.approvalStatus, " +
            "b.statusReason, " +
            "b.keywords ) " +
            "FROM Blog b " +
            "WHERE b.approvalStatus=:approvalStatus")
    List<ViewBlogDto> findBlogsByApprovalStatus(@Param("approvalStatus") String approvalStatus);

    long countByApprovalStatus(String approvalStatus);

    Optional<Blog> findByTitle(String title);
}