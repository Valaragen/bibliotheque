package com.rudy.bibliotheque.mbook.model;

import com.rudy.bibliotheque.mbook.model.common.AbstractEntityComposedId;
import com.rudy.bibliotheque.mbook.model.composedId.BookCopyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Copy extends AbstractEntityComposedId {

    @EmbeddedId
    private BookCopyId id;

    @Column(nullable = false)
    private String stateAtPurchase;
    @Column(nullable = false)
    private String currentState;

    @Column(nullable = false)
    private boolean borrowed;

}
