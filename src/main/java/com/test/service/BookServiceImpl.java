package com.test.service;

import com.test.model.Book;
import com.test.model.Category;
import com.test.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.delete(id);
    }

    @Override
    public Page<Book> findAllByCategory(Category category, Pageable pageable) {
        return bookRepository.findAllByCategory(category,pageable);
    }

    @Override
    public Page<Book> findAllByOrderByPriceAsc(Pageable pageable) {
        return bookRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Book> findAllByOrderByPriceDesc(Pageable pageable) {
        return bookRepository.findAllByOrderByPriceDesc(pageable);
    }

    @Override
    public Page<Book> findAllByOrderByDateOfPurchaseAsc(Pageable pageable) {
        return bookRepository.findAllByOrderByDateOfPurchaseAsc(pageable);
    }

    @Override
    public Page<Book> findAllByOrderByDateOfPurchaseDesc(Pageable pageable) {
        return bookRepository.findAllByOrderByDateOfPurchaseDesc(pageable);
    }
}
