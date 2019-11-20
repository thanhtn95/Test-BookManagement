package com.test.controller;

import com.test.model.Book;
import com.test.model.Category;
import com.test.service.BookService;
import com.test.service.BookServiceImpl;
import com.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute("categories")
    private Iterable<Category> getCategories(){
        return categoryService.findAll();
    }
    @GetMapping("/newBook")
    public ModelAndView getNewBookForm(){
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book",new Book());
        modelAndView.addObject("categories", getCategories());
        return modelAndView;
    }

    @PostMapping("/newBook")
    public ModelAndView doAddNewBook(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView getBookList( @PageableDefault(size = 10)Pageable pageable){
        Page<Book> books = bookService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books",books);
        modelAndView.addObject("categories", getCategories());
        return modelAndView;
    }
    @GetMapping("/{id}/viewBook")
    public ModelAndView getBookView(@PathVariable int id){
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/book/view");
        modelAndView.addObject("book",book);
        return modelAndView;
    }
    @GetMapping("/{id}/deleteBook")
    public ModelAndView doDeleteBook(@PathVariable int id){
        bookService.delete(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @GetMapping("/{id}/editBook")
    public ModelAndView getBookEditView(@PathVariable int id){
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/book/edit");
        modelAndView.addObject("book",book);
        modelAndView.addObject("categories", getCategories());
        return modelAndView;
    }

    @PostMapping("/doEditBook")
    public ModelAndView doEditBook(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @GetMapping("/searchByCategory")
    public ModelAndView getBookByCategory(@RequestParam("searchCategory") int searchCategory, @PageableDefault(size = 10) Pageable pageable){
        Page<Book> books;
        if(searchCategory == -1){
            books = bookService.findAll(pageable);
        }else{
            Category searcCategory = categoryService.findById(searchCategory);
            books = bookService.findAllByCategory(searcCategory,pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books",books);
        modelAndView.addObject("searchCategory",searchCategory);
        modelAndView.addObject("categories", getCategories());
        return modelAndView;
    }
    @GetMapping("/sortByDOP")
    public ModelAndView getListSortedByDOP(@RequestParam("sortDirection1") String sort,  @PageableDefault(size = 10)Pageable pageable){
        Page<Book> books;
        if(sort.equals("no")){
            books = bookService.findAll(pageable);
        }else if(sort.equals("asc")){
            books = bookService.findAllByOrderByDateOfPurchaseAsc(pageable);
        }else{
            books = bookService.findAllByOrderByDateOfPurchaseDesc(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books",books);
        modelAndView.addObject("sortDirection1",sort);
        return modelAndView;
    }

    @GetMapping("/sortByPrice")
    public ModelAndView getListSortedByPrice(@RequestParam("sortDirection2") String sort, @PageableDefault(size = 10) Pageable pageable){
        Page<Book> books;
        if(sort.equals("no")){
            books = bookService.findAll(pageable);
        }else if(sort.equals("asc")){
            books = bookService.findAllByOrderByPriceAsc(pageable);
        }else{
            books = bookService.findAllByOrderByPriceDesc(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books",books);
        modelAndView.addObject("sortDirection2",sort);
        return modelAndView;
    }
}
