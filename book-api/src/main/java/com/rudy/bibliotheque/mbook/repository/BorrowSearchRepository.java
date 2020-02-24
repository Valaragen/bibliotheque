package com.rudy.bibliotheque.mbook.repository;

import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.model.Borrow;
import com.rudy.bibliotheque.mbook.search.BookSearch;
import com.rudy.bibliotheque.mbook.search.LoanSearch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowSearchRepository {
    List<Borrow> findAllBySearch(LoanSearch loanSearch);
}
