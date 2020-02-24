package com.rudy.bibliotheque.batch.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rudy.bibliotheque.batch.dto.BorrowDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanSearchDTO {
    private List<String> status;
}
