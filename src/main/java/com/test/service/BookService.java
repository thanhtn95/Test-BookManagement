package com.test.service;

import com.test.model.Book;
import com.test.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAll(Pageable pageable);
    Book findById(int id);
    void save(Book book);
    void delete(int id);
    Page<Book> findAllByCategory(Category category, Pageable pageable);
    Page<Book> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Book> findAllByOrderByPriceDesc(Pageable pageable);
    Page<Book> findAllByOrderByDateOfPurchaseAsc(Pageable pageable);
    Page<Book> findAllByOrderByDateOfPurchaseDesc(Pageable pageable);
}
