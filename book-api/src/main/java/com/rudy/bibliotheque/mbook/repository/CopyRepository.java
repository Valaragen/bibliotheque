package com.rudy.bibliotheque.mbook.repository;

import com.rudy.bibliotheque.mbook.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CopyRepository extends JpaRepository<Copy, String> {
    Optional<Copy> findByBookId(Long id);
    Optional<Copy> findFirstByBookIdAndBorrowedIsFalse(Long id);
}
