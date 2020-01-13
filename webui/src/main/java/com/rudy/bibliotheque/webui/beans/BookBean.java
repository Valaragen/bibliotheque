package com.rudy.bibliotheque.webui.beans;

import lombok.Data;

import java.util.Date;

@Data
public class BookBean {
    private Long id;
    private Long version;
    private String name;
    private String description;
    private String author;
    private Date releaseDate;
    private Integer copyNumber;
    private Integer availableCopyNumber;
}
