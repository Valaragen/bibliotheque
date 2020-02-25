package com.rudy.bibliotheque.webui.dto;

import lombok.Data;

@Data
public class CopyCreateDTO {
    private Long bookId;
    private String code;
    private String stateAtPurchase;
}
