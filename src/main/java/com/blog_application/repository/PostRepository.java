package com.blog_application.repository;

import com.blog_application.entity.Category;
import com.blog_application.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Page<Post> findAllByCategory(Category category, Pageable pageable);

    Page<Post> findAllByTitleContaining(String query, Pageable pageable);

}
