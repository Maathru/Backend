package com.maathru.backend.Application.controllers;

import com.maathru.backend.Application.dto.request.BlogDto;
import com.maathru.backend.Domain.entity.Blog;
import com.maathru.backend.Domain.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@AllArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping()
    public ResponseEntity<Blog> addBlog(@RequestBody BlogDto blogDto){
        return blogService.addBlog(blogDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable long id){
        return blogService.getBlog(id);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Blog>> getAllBlogs(){
        return blogService.getAllBlogs();
    }
}
