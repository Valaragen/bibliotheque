package com.rudy.bibliotheque.mbook.model;

import com.rudy.bibliotheque.mbook.model.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Borrow extends AbstractEntity {
    @ManyToOne
    private BookCopy bookCopy;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private boolean hasDurationExtended;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date loanStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date loanEndDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date returnedOn;

    @Column(nullable = false)
    private String stateBeforeBorrow;

    private String stateAfterBorrow;

}
