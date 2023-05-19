package com.blog_application.controller;

import com.blog_application.entity.Post;
import com.blog_application.service.CategoryService;
import com.blog_application.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping
    public ModelAndView index(HttpSession session,@PageableDefault(size=2) Pageable pageable) {
        session.setAttribute("categories", categoryService.findAll());
        Sort sort = Sort.by("postTime").descending();
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Post> postList = postService.findAll(pageRequest);
        return new ModelAndView("index", "postList", postList);
    }
}
