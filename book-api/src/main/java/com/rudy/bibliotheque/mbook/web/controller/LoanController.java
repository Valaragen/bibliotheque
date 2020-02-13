package com.rudy.bibliotheque.mbook.web.controller;

import com.rudy.bibliotheque.mbook.DTO.BorrowDTO;
import com.rudy.bibliotheque.mbook.model.Borrow;
import com.rudy.bibliotheque.mbook.service.BorrowService;
import com.rudy.bibliotheque.mbook.util.Constant;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @PreAuthorize("hasRole('"+ Constant.STAFF_ROLE_NAME +"')")
    @GetMapping
    public List<BorrowDTO> getAllLoans(){
        return borrowService.convertBorrowsToDTOs(borrowService.getAllLoans());
    }

    /**
     * Return loans of the user with the parameter id
     * @param id id of the user
     * @return List of loans as a DTO
     */
    @PreAuthorize("hasRole('" + Constant.STAFF_ROLE_NAME + "')")
    @GetMapping(Constant.USERS_PATH + Constant.SLASH_ID)
    public List<BorrowDTO> getLoansByUserId(@PathVariable String id){
        return borrowService.convertBorrowsToDTOs(borrowService.getAllLoansByUserId(id));
    }

    /**
     * Return loans of the current user
     * @return List of loans as a DTO
     */
    @PreAuthorize("hasRole('" + Constant.USER_ROLE_NAME + "')")
    @GetMapping(Constant.USERS_PATH + "/current")
    public List<BorrowDTO> getLoansByCurrentUser(){
        return borrowService.convertBorrowsToDTOs(borrowService.getAllLoansByUserId(getUserIdFromToken()));
    }

    @PreAuthorize("hasRole('" + Constant.USER_ROLE_NAME + "')")
    @PostMapping(Constant.USERS_PATH)
    public List<BorrowDTO> createBorrowRequest(BorrowDTO borrowDTO){

        return new ArrayList<>();
    }

    /**
     * Get all expired and non returned loans from the database
     * @return List of loans as a DTO that contains useful informations about the related book and user
     */
    @PreAuthorize("hasRole('" + Constant.STAFF_ROLE_NAME + "')")
    @GetMapping(Constant.NONRETURNED_EXPIRED_LOANS_PATH)
    public List<BorrowDTO> getAllNonReturnedExpiredLoans(){

        long millis=System.currentTimeMillis();
        Date date = new Date(millis);

        List<Borrow> loans = borrowService.getAllNonReturnedExpiredLoans(date);

        return borrowService.convertBorrowsToDTOs(loans);
    }

    private String getUserIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakAuthenticationToken kp = (KeycloakAuthenticationToken) authentication;
        return kp.getAccount().getKeycloakSecurityContext().getToken().getSubject();
    }
}
