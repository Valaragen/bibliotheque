package com.rudy.bibliotheque.mbook.repository;

import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.search.BookSearch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookSearchRepository {
    List<Book> findAllBySearch(BookSearch bookSearch);
}
