package com.rudy.bibliotheque.batch.model;

import com.rudy.bibliotheque.batch.model.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Borrow extends AbstractEntity {
    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    private boolean hasDurationExtended;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date loanStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date loanEndDate;

    //TODO état à l'empreint
    //TODO état après l'empreint

}
