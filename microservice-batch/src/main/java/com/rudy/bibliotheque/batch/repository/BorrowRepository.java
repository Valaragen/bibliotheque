package com.rudy.bibliotheque.batch.repository;

import com.rudy.bibliotheque.batch.model.Borrow;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Page<Borrow> findAllByLoanEndDateBefore(Date loanEndDate, org.springframework.data.domain.Pageable pageable);
}
