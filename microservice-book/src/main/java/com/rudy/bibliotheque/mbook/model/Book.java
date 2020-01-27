package com.rudy.bibliotheque.mbook.model;

import com.rudy.bibliotheque.mbook.model.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date releaseDate;

    private Integer copyNumber;

    private Integer availableCopyNumber;
}
