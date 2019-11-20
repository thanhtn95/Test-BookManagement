package com.test.controller;

import com.test.model.Book;
import com.test.model.Category;
import com.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categoryList")
    public ModelAndView getIndex(@PageableDefault(size = 10) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/category/list");
        Page<Category> categories = categoryService.findAll(pageable);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/newCategory")
    public ModelAndView getNewCategoryForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create","category",new Category());
        return modelAndView;
    }

    @PostMapping("/newCategory")
    public ModelAndView doAddNewCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("redirect:/categoryList");
        return modelAndView;
    }

    @GetMapping("/{id}/viewCategory")
    public ModelAndView getCategory(@PathVariable int id){
        Category category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category",category);
        return modelAndView;
    }
    @GetMapping("/{id}/deleteCategory")
    public ModelAndView deleteCategory(@PathVariable int id){
        categoryService.delete(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/categoryList");
        return modelAndView;
    }

    @GetMapping("/{id}/editCategory")
    public ModelAndView getEditCategoryForm(@PathVariable int id){
        Category category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category",category);
        return modelAndView;
    }

    @PostMapping("/doEditCategory")
    public ModelAndView doEditCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("redirect:/categoryList");
        return modelAndView;
    }

}
