package com.rudy.bibliotheque.batch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rudy.bibliotheque.batch.dto.composedId.BookCopyDTOId;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CopyDTO {

    private BookCopyDTOId id;

    private String stateAtPurchase;
    private String currentState;
    private boolean borrowed;
}
