package com.rudy.bibliotheque.webui.controller;

import com.rudy.bibliotheque.webui.dto.BorrowDTO;
import com.rudy.bibliotheque.webui.proxies.BookApiProxy;
import com.rudy.bibliotheque.webui.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(Constant.LOANS_PATH)
public class LoanController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private BookApiProxy bookApiProxy;

    @Autowired
    public LoanController(BookApiProxy bookApiProxy){
        this.bookApiProxy = bookApiProxy;
    }

    @PreAuthorize("hasRole('"+ Constant.STAFF_ROLE_NAME +"')")
    @GetMapping
    public String getLoansPage(Model model) {
        model.addAttribute("loans", bookApiProxy.getAllLoans());
        return Constant.LOANS_LIST_PAGE;
    }

    @PreAuthorize("hasRole('"+ Constant.USER_ROLE_NAME +"')")
    @GetMapping(Constant.MY_LOANS_PATH)
    public String getUserLoans(Model model) {
        model.addAttribute("currentLoans", bookApiProxy.getLoansByCurrentUser());
        return Constant.LOANS_USER_PAGE;
    }

    @PreAuthorize("hasRole('"+ Constant.USER_ROLE_NAME +"')")
    @PostMapping(Constant.SLASH_ID_PATH + Constant.EXTEND_PATH)
    public String extendMyBorrow(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        ResponseEntity<BorrowDTO> newBorrowDTO = bookApiProxy.extendMyLoan(id);

        if (newBorrowDTO.getStatusCode() != HttpStatus.NO_CONTENT) {
            //TODO add logic
            log.error("Can't extend borrow duration");
        }

        return Constant.REDIRECT + httpServletRequest.getHeader("referer");
    }
}
