package com.rudy.bibliotheque.webui.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookSearchDTO {
    private String isbn;
    private String name;
    private String description;
    private String author;
    private String publisher;

    private Date releaseDate;

    private Integer copyNumber;

    private Integer availableCopyNumber;
}
