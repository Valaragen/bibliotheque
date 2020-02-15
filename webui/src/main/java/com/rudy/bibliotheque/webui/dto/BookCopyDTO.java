package com.rudy.bibliotheque.webui.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rudy.bibliotheque.webui.dto.composedId.BookCopyDTOId;
import com.rudy.bibliotheque.webui.util.Constant;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookCopyDTO {

    private BookCopyDTOId id;

    private String stateAtPurchase;
    private String currentState;
    private boolean isBorrowed;
}
