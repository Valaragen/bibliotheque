package com.rudy.bibliotheque.mbook.service;

import com.rudy.bibliotheque.mbook.DTO.BorrowDTO;
import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.model.Borrow;
import com.rudy.bibliotheque.mbook.model.User;
import com.rudy.bibliotheque.mbook.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BorrowService {

    private BorrowRepository borrowRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }


    public List<Borrow> getAllLoans() {
        return borrowRepository.findAll();
    }

    public Borrow getLoanById(Long id) {
        return borrowRepository.findById(id).orElse(null);
    }

    public List<Borrow> getAllNonReturnedExpiredLoans(Date date) {
        return borrowRepository.findAllByReturnedOnIsNullAndLoanEndDateBefore(date);
    }

    public BorrowDTO convertBorrowToDTO(Borrow borrow) {
        BorrowDTO borrowDTO = new BorrowDTO();
        Book borrowedBook = borrow.getBookCopy().getId().getBook();
        User borrowingUser = borrow.getUser();

        borrowDTO.setUserUsername(borrowingUser.getUsername());
        borrowDTO.setUserEmail(borrowingUser.getEmail());

        borrowDTO.setLoanStartDate(borrow.getLoanStartDate());
        borrowDTO.setLoanEndDate(borrow.getLoanEndDate());

        borrowDTO.setBookIsbn(borrowedBook.getIsbn());
        borrowDTO.setBookName(borrowedBook.getName());

        return borrowDTO;
    }

    public List<BorrowDTO> convertBorrowsToDTOs(List<Borrow> borrows) {
        List<BorrowDTO> bookDTOs = new ArrayList<>();
        for (Borrow borrow : borrows) {
            bookDTOs.add(convertBorrowToDTO(borrow));
        }
        return bookDTOs;
    }

}
