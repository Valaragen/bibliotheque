package com.rudy.bibliotheque.webui.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CopyDTO {

    private String code;

    private BookDTO book;

    private String stateAtPurchase;
    private String currentState;
    private boolean borrowed;
}
