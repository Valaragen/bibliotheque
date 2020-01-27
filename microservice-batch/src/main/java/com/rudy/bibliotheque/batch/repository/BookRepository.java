package com.rudy.bibliotheque.batch.repository;

import com.rudy.bibliotheque.batch.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
