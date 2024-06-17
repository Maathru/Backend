package com.maathru.backend.Application.controllers.v1;

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
    public ResponseEntity<Blog> addBlog(@RequestBody BlogDto blogDto) {
        return blogService.addBlog(blogDto);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Blog>> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable long id) {
        return blogService.getBlog(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable long id, @RequestBody BlogDto blogDto) {
        return blogService.updateBlog(id, blogDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable long id) {
        return blogService.deleteBlog(id);
    }

}
