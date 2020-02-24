package com.rudy.bibliotheque.mbook.web.controller;

import com.rudy.bibliotheque.mbook.config.ApplicationPropertiesConfig;
import com.rudy.bibliotheque.mbook.model.Borrow;
import com.rudy.bibliotheque.mbook.search.LoanSearch;
import com.rudy.bibliotheque.mbook.service.BorrowService;
import com.rudy.bibliotheque.mbook.util.Constant;
import com.rudy.bibliotheque.mbook.web.controller.util.ControllerUtil;
import com.rudy.bibliotheque.mbook.web.exception.CRUDIssueException;
import com.rudy.bibliotheque.mbook.web.exception.NotFoundException;
import com.rudy.bibliotheque.mbook.web.exception.ProhibitedActionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(Constant.LOANS_PATH)
public class LoanController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ApplicationPropertiesConfig appProperties;

    private BorrowService borrowService;

    @Autowired
    public LoanController(BorrowService borrowService, ApplicationPropertiesConfig appProperties) {
        this.borrowService = borrowService;
        this.appProperties = appProperties;
    }

    /**
     * Get all the loans from the database
     * @return List of loans that contains useful informations about the related book and user
     */
    @PreAuthorize("hasRole('"+ Constant.STAFF_ROLE_NAME +"')")
    @GetMapping
    public List<Borrow> getAllLoans(@ModelAttribute("loanSearch") LoanSearch loanSearch){
        return borrowService.getLoansBySearch(loanSearch);
    }

    /**
     * Get all the loans from the database
     * @return List of loans that contains useful informations about the related book and user
     */
    @PreAuthorize("hasRole('"+ Constant.STAFF_ROLE_NAME +"')")
    @PutMapping(Constant.SLASH_ID_PATH + Constant.VALIDATE_PATH)
    public ResponseEntity<Borrow> validateALoan(@PathVariable Long id) {
        Borrow borrow = borrowService.getLoanById(id);
        if (borrow == null) {
            throw new NotFoundException("Can't find loan with id " + id);
        }
        if (borrow.getLoanStartDate() != null) {
            throw new ProhibitedActionException("This loan is already validated");
        }

        //create borrow request process
        Date today = new Date();
        borrow.setLoanStartDate(today);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, appProperties.getLoanTimeInDays());
        borrow.setLoanEndDate(calendar.getTime());

        Borrow newBorrow = borrowService.saveLoan(borrow);

        if (newBorrow == null) throw new CRUDIssueException("Can't' update loan");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
