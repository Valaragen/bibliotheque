package com.rudy.biliotheque.mbook.model;

import com.rudy.biliotheque.mbook.model.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Book extends AbstractEntity {
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 5000)
    private String description;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private Date releaseDate;
    private Integer copyNumber;
    private Integer availableCopyNumber;
}
