package com.rudy.bibliotheque.mbook.repository;

import com.rudy.bibliotheque.mbook.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUserInfoId(String id);
    List<Borrow> findAllByReturnedOnIsNullAndLoanEndDateBefore(Date loanEndDate);
}
