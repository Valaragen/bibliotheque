package com.rudy.bibliotheque.batch.repository;

import com.rudy.bibliotheque.batch.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findAllByLoanEndDateBefore(Date date);
}
