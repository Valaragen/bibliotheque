package com.rudy.bibliotheque.mbook.dto;

import lombok.Data;

@Data
public class LoanCreateDTO {
    private String userId;
    private String code;
    private Long bookId;
}
