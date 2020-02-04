package com.rudy.bibliotheque.mbook.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class BorrowDTO {
    private String userUsername;
    private String userEmail;
    private Date loanStartDate;
    private Date loanEndDate;
    private String bookIsbn;
    private String bookName;
}
