package com.rudy.bibliotheque.mbook.web.controller;

import com.rudy.bibliotheque.mbook.config.ApplicationPropertiesConfig;
import com.rudy.bibliotheque.mbook.model.Borrow;
import com.rudy.bibliotheque.mbook.service.BorrowService;
import com.rudy.bibliotheque.mbook.util.Constant;
import com.rudy.bibliotheque.mbook.web.exception.CRUDIssueLoanException;
import com.rudy.bibliotheque.mbook.web.exception.LoanNotFoundException;
import com.rudy.bibliotheque.mbook.web.exception.ProhibitedActionException;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    public List<Borrow> getAllLoans(){
        return borrowService.getAllLoans();
    }

    /**
     * Return loans of the user with the parameter id
     * @param id id of the user
     * @return List of loans
     */
    @PreAuthorize("hasRole('" + Constant.STAFF_ROLE_NAME + "')")
    @GetMapping(Constant.USERS_PATH + Constant.SLASH_ID)
    public List<Borrow> getLoansByUserId(@PathVariable String id){
        return borrowService.getAllLoansByUserId(id);
    }

    /**
     * Return loans of the current user
     * @return List of loans
     */
    @PreAuthorize("hasRole('" + Constant.USER_ROLE_NAME + "')")
    @GetMapping(Constant.USERS_PATH + "/current")
    public List<Borrow> getLoansByCurrentUser(){
        return borrowService.getAllLoansByUserId(getUserIdFromToken());
    }

    @PreAuthorize("hasRole('" + Constant.USER_ROLE_NAME + "')")
    @PostMapping(Constant.USERS_PATH)
    public List<Borrow> createBorrowRequest(Borrow borrowDTO){
        System.out.println(appProperties.getLoanTimeInDays());
        return new ArrayList<>();
    }

    /**
     * Get all expired and non returned loans from the database
     * @return List of loans that contains useful informations about the related book and user
     */
    @PreAuthorize("hasRole('" + Constant.STAFF_ROLE_NAME + "')")
    @GetMapping(Constant.NONRETURNED_EXPIRED_LOANS_PATH)
    public List<Borrow> getAllNonReturnedExpiredLoans(){

        long millis=System.currentTimeMillis();
        Date date = new Date(millis);

        List<Borrow> loans = borrowService.getAllNonReturnedExpiredLoans(date);

        return loans;
    }


    @PreAuthorize("hasRole('" + Constant.USER_ROLE_NAME + "')")
    @PutMapping(Constant.SLASH_ID + Constant.EXTEND_PATH)
    public ResponseEntity<Borrow> extendMyLoan(@PathVariable Long id){
        String tokenSubjectId = getUserIdFromToken();
        Borrow currentLoan = borrowService.getLoanById(id);
        if(currentLoan == null) {
            throw new LoanNotFoundException("Aucun emprunt d'id " + id + " n'a été trouvé");
        }
        if (!tokenSubjectId.equals(currentLoan.getUserInfo().getId())) {
            log.warn("user with id " + tokenSubjectId + " tried to extend a loan he does not have");
            throw new ProhibitedActionException("Action interdite");
        }

        long loanTimeInTimestamp = (currentLoan.getLoanEndDate().getTime() - currentLoan.getLoanStartDate().getTime());

        currentLoan.setLoanEndDate(new Date(currentLoan.getLoanEndDate().getTime() + loanTimeInTimestamp));
        currentLoan.setHasDurationExtended(true);
        Borrow newLoan = borrowService.saveLoan(currentLoan);
        if(newLoan == null) throw new CRUDIssueLoanException("Impossible de mettre à jour l'emprunt");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String getUserIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakAuthenticationToken kp = (KeycloakAuthenticationToken) authentication;
        return kp.getAccount().getKeycloakSecurityContext().getToken().getSubject();
    }
}
