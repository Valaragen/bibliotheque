package com.rudy.biliotheque.mbook.model;

import com.rudy.biliotheque.mbook.model.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Book extends AbstractEntity {
    private String name;
    private String description;
    private String author;
    private Date releaseDate;
    private Integer copyNumber;
    private Integer availableCopyNumber;
}
