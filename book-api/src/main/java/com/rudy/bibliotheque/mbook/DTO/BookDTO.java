package com.rudy.bibliotheque.mbook.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class BookDTO {
    private Long id;
    private String isbn;
    private String name;
    private String description;
    private String author;
    private String publisher;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    private Integer copyNumber;

    private Integer availableCopyNumber;
}
