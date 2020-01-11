package com.rudy.biliotheque.mouvrage.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
public class Ouvrage {
    private String name;
    private String description;
    private String author;
    private Date releaseDate;
    private Integer copyNumber;
    private Integer availableCopyNumber;
}
