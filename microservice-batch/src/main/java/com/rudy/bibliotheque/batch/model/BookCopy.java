package com.rudy.bibliotheque.batch.model;

import com.rudy.bibliotheque.batch.model.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class BookCopy extends AbstractEntity {
    private String code;
    private String stateAtPurchase;
    private String currentState;

    private boolean isBorrowed;

    @ManyToOne
    private Book book;
}
