package com.rudy.biliotheque.mouvrage.repository;

import com.rudy.biliotheque.mouvrage.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
