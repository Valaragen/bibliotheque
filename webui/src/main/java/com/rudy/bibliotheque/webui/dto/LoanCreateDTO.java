package com.rudy.bibliotheque.webui.dto;

import lombok.Data;

@Data
public class LoanCreateDTO {
    private String userId;
    private String code;
    private Long bookId;
}
