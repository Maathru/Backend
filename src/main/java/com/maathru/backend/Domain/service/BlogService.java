package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.BlogDto;
import com.maathru.backend.Application.dto.response.ViewBlogDto;
import com.maathru.backend.Domain.entity.Blog;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.maathru.backend.External.repository.BlogRepository;

import java.util.List;
import java.util.Optional;

import static com.maathru.backend.constant.Constant.PENDING_BLOG;

@Service
@AllArgsConstructor
@Slf4j
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ResponseEntity<String> addBlog(BlogDto blogDto) {
        User user = jwtService.getCurrentUser();

        try {
            Blog blog = new Blog();
            blog.setTitle(blogDto.getTitle());
            blog.setCategory(blogDto.getCategory());
            blog.setContent(blogDto.getContent());
            blog.setAdditionalNotes(blogDto.getAdditionalNotes());
            blog.setKeywords(blogDto.getKeywords());
            blog.setAdditionalNotes("Pending");
            blog.setApprovalStatus(PENDING_BLOG);

            blog.setCreatedBy(user);
            blog.setUpdatedBy(user);

            blog = blogRepository.save(blog);

            log.info("Blog added successfully By: {}", user.getEmail());
            return ResponseEntity.status(201).body("Blog added successfully");
        } catch (Exception e) {
            log.error("Error adding blog: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error adding blog");
        }
    }

    public ResponseEntity<Blog> getBlog(Long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isPresent()) {
            return ResponseEntity.ok(optionalBlog.get());
        } else {
            log.error("Blog not found");
            throw new NotFoundException("Blog not found");
        }
    }

    public ResponseEntity<Iterable<ViewBlogDto>> getAllBlogs() {
        List<ViewBlogDto> blogs = blogRepository.findAllBlogsForDemo();

//        if (blogs.isEmpty()) {
//            log.error("Blogs not found");
//            throw new NotFoundException("Blogs not found");
//        }
//        log.info("Getting all blogs");
//
//        List<ViewBlogDto> viewBlogDtos = blogs.stream().map(blog -> {
//            ViewBlogDto viewBlogDto = new ViewBlogDto();
//            viewBlogDto.setTitle(blog.getTitle());
//            viewBlogDto.setCategory(blog.getCategory());
//            viewBlogDto.setContent(blog.getContent());
//            viewBlogDto.setKeywords(blog.getKeywords());
//            log.info(blog.getContent());
//            return viewBlogDto;
//        }).toList();

        return ResponseEntity.ok(blogs);
    }

//    public ResponseEntity<Blog> updateBlog(long id, BlogDto blogDto) {
//        Optional<Blog> optionalBlog = blogRepository.findById(id);
//        Optional<User> optionalUser = userRepository.findById(blogDto.getAuthor());
//
//        if (optionalBlog.isPresent()) {
//            if (optionalUser.isPresent()) {
//                Blog blog = optionalBlog.get();
//                blog.setTitle(blogDto.getTitle());
//                blog.setContent(blogDto.getContent());
//                blog.setCategory(blogDto.getCategory());
//                blog.setImage(blogDto.getImage());
//                blog.setLocation(blogDto.getLocation());
//                blog.setCreatedBy(optionalUser.get());
//                blog = blogRepository.save(blog);
//
//                return ResponseEntity.status(201).body(blog);
//            } else {
//                log.error("Author not found");
//                throw new NotFoundException("Author not found");
//            }
//        } else {
//            log.error("Blogs not found");
//            throw new NotFoundException("Blogs not found");
//        }
//    }

    public ResponseEntity<Blog> deleteBlog(long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isPresent()) {
            blogRepository.delete(optionalBlog.get());
            return ResponseEntity.ok().body(optionalBlog.get());
        } else {
            log.error("Blog not found");
            throw new NotFoundException("Blog not found");
        }
    }
}
