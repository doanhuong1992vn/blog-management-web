package com.blog_application.service.impl;

import com.blog_application.entity.Category;
import com.blog_application.entity.Post;
import com.blog_application.repository.PostRepository;
import com.blog_application.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void save(Post post) {
        post.setPostTime(LocalDateTime.now());
        postRepository.save(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void update(Post post) {
        postRepository.save(post);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> findAllByCategory(Category category, Pageable pageable) {
        return postRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Page<Post> findAllByTitleContaining(String query, Pageable pageRequest) {
        return postRepository.findAllByTitleContaining(query, pageRequest);
    }
}
