package com.test.controller;

import com.test.model.Category;
import com.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Category>> listAllCategory() {
        Iterable<Category> categories = categoryService.findAll();
        if (categories == null) {
            return new ResponseEntity<Iterable<Category>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Iterable<Category>>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}/viewCategory")
    public ResponseEntity<Category>viewCategory(@PathVariable int id){
        Category category =categoryService.findById(id);
        if(category == null){
            return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Category>(category,HttpStatus.OK);
    }

    @PostMapping("/newCategory")
    public ResponseEntity<Void> createCategory(@RequestBody Category category, UriComponentsBuilder uriComponentsBuilder){
        categoryService.save(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/{id}/viewCategory").buildAndExpand(category.getId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }

    @PutMapping("/{id}/updateCategory")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category){
        Category currentCategory = categoryService.findById(id);
        if(currentCategory == null){
            return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
        }
        currentCategory.setId(category.getId());
        currentCategory.setName(category.getName());
        currentCategory.setDescription(category.getDescription());

        categoryService.save(currentCategory);
        return new ResponseEntity<Category>(currentCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{id}/deleteCategory")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id, UriComponentsBuilder uriComponentsBuilder){
        Category category = categoryService.findById(id);
        if(category == null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(id);
         return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
