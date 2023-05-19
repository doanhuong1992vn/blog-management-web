package com.blog_application.controller;

import com.blog_application.entity.Category;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final PostService postService;

    @PostMapping("/create")
    public String create(String nameCategory, HttpServletRequest request) {
        categoryService.save(new Category(nameCategory));
        request.getSession().setAttribute("categories", categoryService.findAll());
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/view/{id}/posts")
    public ModelAndView view(@PathVariable Long id, @PageableDefault(size = 2) Pageable pageable) {
        Optional<Category> category = categoryService.findById(id);
        return category.map(value -> {
                    ModelAndView modelAndView = new ModelAndView("posts-by-category");
                    Sort sort = Sort.by("postTime").descending();
                    Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
                    Page<Post> postList = postService.findAllByCategory(value, pageRequest);
                    modelAndView.addObject("postList", postList);
                    modelAndView.addObject("category", value);
                    return modelAndView;
                })
                .orElseGet(() -> new ModelAndView("404"));
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        return category.map(value -> new ModelAndView("category", "category", value))
                .orElseGet(() -> new ModelAndView("404"));
    }

    @PostMapping("/update")
    public String update(Category category, HttpSession session) {
        categoryService.update(category);
        session.setAttribute("categories", categoryService.findAll());
        return "redirect:/category/edit/" + category.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/";
    }
}
