package com.blog_application.service;

import com.blog_application.entity.Category;
import com.blog_application.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService extends GeneralService<Post> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByCategory(Category value, Pageable pageable);

    Page<Post> findAllByTitleContaining(String query, Pageable pageRequest);
}
