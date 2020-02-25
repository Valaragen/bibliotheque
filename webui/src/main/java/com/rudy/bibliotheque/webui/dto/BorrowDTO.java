package com.rudy.bibliotheque.webui.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BorrowDTO {
    private Long id;

    private CopyDTO copy;

    private UserInfoDTO userInfo;

    private boolean hasDurationExtended;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loanRequestDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadlineToRetrieve;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loanStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loanEndDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnedOn;

    private String stateBeforeBorrow;

    private String stateAfterBorrow;
}
