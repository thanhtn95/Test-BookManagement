package com.test.repository;

import com.test.model.Book;
import com.test.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book,Integer> {
    Page<Book> findAllByCategory(Category category, Pageable pageable);
    Page<Book> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Book> findAllByOrderByPriceDesc(Pageable pageable);
    Page<Book> findAllByOrderByDateOfPurchaseAsc(Pageable pageable);
    Page<Book> findAllByOrderByDateOfPurchaseDesc(Pageable pageable);
}
