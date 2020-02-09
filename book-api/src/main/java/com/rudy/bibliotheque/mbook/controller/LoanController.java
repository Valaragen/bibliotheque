package com.rudy.bibliotheque.mbook.controller;

import com.rudy.bibliotheque.mbook.DTO.BorrowDTO;
import com.rudy.bibliotheque.mbook.model.Borrow;
import com.rudy.bibliotheque.mbook.service.BorrowService;
import com.rudy.bibliotheque.mbook.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(Constant.LOANS_PATH)
public class LoanController {

    private BorrowService borrowService;

    @Autowired
    public LoanController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    /**
     * Get all the loans from the database
     * @return List of loans as a DTO that contains useful informations about the related book and user
     */
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping
    public List<BorrowDTO> getAllLoans(){
        return borrowService.convertBorrowsToDTOs(borrowService.getAllLoans());
    }

    /**
     * Get all expired and non returned loans from the database
     * @return List of loans as a DTO that contains useful informations about the related book and user
     */
    @GetMapping(Constant.NONRETURNED_EXPIRED_LOANS_PATH)
    public List<BorrowDTO> getAllNonReturnedExpiredLoans(){

        long millis=System.currentTimeMillis();
        Date date = new Date(millis);

        List<Borrow> loans = borrowService.getAllNonReturnedExpiredLoans(date);

        return borrowService.convertBorrowsToDTOs(loans);
    }
}
