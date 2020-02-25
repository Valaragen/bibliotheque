package com.rudy.bibliotheque.mbook.model;

import com.rudy.bibliotheque.mbook.model.common.AbstractEntityComposedId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Copy extends AbstractEntityComposedId {

    @Id
    private String code;

    @ManyToOne
    private Book book;

    @Column(nullable = false)
    private String stateAtPurchase;
    @Column(nullable = false)
    private String currentState;

    @Column(nullable = false)
    private boolean borrowed;

}
