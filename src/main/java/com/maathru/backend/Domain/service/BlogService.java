package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.BlogDto;
import com.maathru.backend.Domain.entity.Blog;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.BlogNotFoundException;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.maathru.backend.External.repository.BlogRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public ResponseEntity<Blog> addBlog(BlogDto blogDto) {
        Optional<User> optionalUser = userRepository.findById(blogDto.getAuthor());

        if (optionalUser.isPresent()) {
            Blog blog = new Blog();
            blog.setTitle(blogDto.getTitle());
            blog.setContent(blogDto.getContent());
            blog.setCategory(blogDto.getCategory());
            blog.setImage(blogDto.getImage());
            blog.setLocation(blogDto.getLocation());
            blog.setAuthor(optionalUser.get());
            blogRepository.save(blog);

            return ResponseEntity.status(201).body(blog);
        } else {
            log.error("Author not found");
            throw new UserNotFoundException("Author not found");
        }
    }

    public ResponseEntity<Blog> getBlog(Long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isPresent()) {
            return ResponseEntity.ok(optionalBlog.get());
        } else {
            log.error("Blog not found");
            throw new BlogNotFoundException("Blog not found");
        }
    }

    public ResponseEntity<Iterable<Blog>> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();

        if (blogs.isEmpty()) {
            log.error("Blogs not found");
            throw new BlogNotFoundException("Blogs not found");
        }

        return ResponseEntity.ok(blogs);
    }

    public ResponseEntity<Blog> updateBlog(long id, BlogDto blogDto) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(blogDto.getAuthor());

        if (optionalBlog.isPresent()) {
            if (optionalUser.isPresent()) {
                Blog blog = optionalBlog.get();
                blog.setTitle(blogDto.getTitle());
                blog.setContent(blogDto.getContent());
                blog.setCategory(blogDto.getCategory());
                blog.setImage(blogDto.getImage());
                blog.setLocation(blogDto.getLocation());
                blog.setAuthor(optionalUser.get());
                blogRepository.save(blog);

                return ResponseEntity.status(201).body(blog);
            } else {
                log.error("Author not found");
                throw new UserNotFoundException("Author not found");
            }
        } else {
            log.error("Blogs not found");
            throw new BlogNotFoundException("Blogs not found");
        }
    }

    public ResponseEntity<Blog> deleteBlog(long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isPresent()) {
            blogRepository.delete(optionalBlog.get());
            return ResponseEntity.ok().body(optionalBlog.get());
        } else {
            log.error("Blog not found");
            throw new BlogNotFoundException("Blog not found");
        }
    }
}
