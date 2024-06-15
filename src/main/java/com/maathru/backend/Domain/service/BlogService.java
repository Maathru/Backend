package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.BlogDto;
import com.maathru.backend.Domain.entity.Blog;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.maathru.backend.External.repository.BlogRepository;

@Service
@AllArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public ResponseEntity<Blog> addBlog(BlogDto blogDto){
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setAuthor(blogDto.getAuthor());
        blog.setImage(blogDto.getImage());
        blog.setLocation(blogDto.getLocation());
        blog.setApprovalStatus(blogDto.getApprovalStatus());
        blog.setStatusReason(blogDto.getStatusReason());

        blog = blogRepository.save(blog);
        return ResponseEntity.status(201).body(blog);
    }

    public ResponseEntity<Blog> getBlog(Long id){
        Blog blog = blogRepository.findById(id).orElse(null);
        if(blog == null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(blog);
    }

    public ResponseEntity<Iterable<Blog>> getAllBlogs() {
        Iterable<Blog> blogs = blogRepository.findAll();
        return ResponseEntity.status(200).body(blogs);
    }
}
