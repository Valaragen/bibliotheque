package com.rudy.bibliotheque.mbook.search;

import lombok.Data;

import java.util.Date;

@Data
public class BookSearch {
    private String isbn;
    private String name;
    private String description;
    private String author;
    private String publisher;

    private Date releaseDate;

    private Integer copyNumber;

    private Integer availableCopyNumber;
}
