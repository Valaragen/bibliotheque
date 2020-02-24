package com.rudy.bibliotheque.mbook.repository;

import com.rudy.bibliotheque.mbook.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {
    Optional<Copy> findByIdCodeAndIdBookId(String code, Long id);
    Optional<Copy> findByIdBookId(Long id);
    Optional<Copy> findFirstByIdBookIdAndBorrowedIsFalse(Long id);
}
