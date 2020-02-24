package com.rudy.bibliotheque.webui.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rudy.bibliotheque.webui.dto.BorrowDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanSearchDTO {
    private List<String> status;
}
