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

import static com.maathru.backend.constant.Constant.APPROVED_BLOG;
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
//            blog.setImage(blogDto.getImage());

            // Check if the image is empty or null and set a default value
            String image = blogDto.getImage();
            if (image == null || image.trim().isEmpty()) {
                image = "http://localhost:8081/images/defaultBlog.jpg"; // Replace with your actual default value
            }
            blog.setImage(image);

            blog.setApprovalStatus(PENDING_BLOG);

            blog.setCreatedBy(user);
            blog.setUpdatedBy(user);

            blog = blogRepository.save(blog);

            log.info("Blog added successfully By: {}", user.getEmail());
            return ResponseEntity.status(201).body("Blog added Successfully. Pending review.");
        } catch (Exception e) {
            log.error("Error adding blog: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error adding blog");
        }
    }

    public ResponseEntity<Iterable<ViewBlogDto>> getPendingBlogs() {
        List<ViewBlogDto> blogs = blogRepository.findBlogsByApprovalStatus(PENDING_BLOG);
        return ResponseEntity.ok(blogs);
    }

    public ResponseEntity<Iterable<ViewBlogDto>> getApprovedBlogs() {
        List<ViewBlogDto> blogs = blogRepository.findBlogsByApprovalStatus(APPROVED_BLOG);
        return ResponseEntity.ok(blogs);
    }

//    public ResponseEntity<Blog> getBlog(Long id) {
//        Optional<Blog> optionalBlog = blogRepository.findById(id);
//
//        if (optionalBlog.isPresent()) {
//            return ResponseEntity.ok(optionalBlog.get());
//        } else {
//            log.error("Blog not found");
//            throw new NotFoundException("Blog not found");
//        }
//    }

    public ResponseEntity<Optional<ViewBlogDto>> findArticle(Long id) {

        Optional<ViewBlogDto> article = blogRepository.findArticle(id);

        if (article.isPresent()) {
            return ResponseEntity.ok(article);
        } else {
            log.error("Article with id {} not found", id);
            throw new NotFoundException("Article with id " + id + " not found");
        }
    }

    public ResponseEntity<Iterable<ViewBlogDto>> getAllBlogs() {
        List<ViewBlogDto> blogs = blogRepository.findAllBlogsForDemo();
        return ResponseEntity.ok(blogs);
    }

    public ResponseEntity<String> approveArticle(Long id) {
       Optional <Blog> blog = blogRepository.findById(id);
       if (blog.isPresent()) {
           Blog article = blog.get();
           article.setApprovalStatus(APPROVED_BLOG);
           blogRepository.save(article);

           return ResponseEntity.ok("Article approved successfully");
       } else {
           log.error("Article with id {} not found", id);
           throw new NotFoundException("Article with id " + id + " not found");
       }
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
