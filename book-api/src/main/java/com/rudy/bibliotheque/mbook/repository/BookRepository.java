package com.rudy.bibliotheque.mbook.repository;

import com.rudy.bibliotheque.mbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookSearchRepository {

}
