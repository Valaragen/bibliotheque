package com.rudy.biliotheque.mbook.repository;

import com.rudy.biliotheque.mbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
