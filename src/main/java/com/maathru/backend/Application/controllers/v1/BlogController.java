package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.BlogDto;
import com.maathru.backend.Application.dto.response.ViewBlogDto;
import com.maathru.backend.Domain.entity.Blog;
import com.maathru.backend.Domain.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/blogs")
@Slf4j
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping()
    public ResponseEntity<String> addBlog(@RequestBody @Valid BlogDto blogDto) {
        return blogService.addBlog(blogDto);
    }

    @GetMapping()
    public ResponseEntity<Iterable<ViewBlogDto>> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/pending")
    public ResponseEntity<Iterable<ViewBlogDto>> getPendingBlogs() {
        return blogService.getPendingBlogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ViewBlogDto>> findArticle(@PathVariable Long id) { return blogService.findArticle(id); }

//    @PutMapping("/{id}")
//    public ResponseEntity<Blog> updateBlog(@PathVariable long id, @RequestBody BlogDto blogDto) {
//        return blogService.updateBlog(id, blogDto);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable long id) {
        return blogService.deleteBlog(id);
    }

}
