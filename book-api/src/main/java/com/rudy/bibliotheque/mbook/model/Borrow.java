package com.rudy.bibliotheque.mbook.model;

import com.rudy.bibliotheque.mbook.model.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
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
    private UserInfo userInfo;

    @Column(nullable = false)
    private boolean hasDurationExtended;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date loanRequestDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date deadlineToRetrieve;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date loanStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date loanEndDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date returnedOn;

    private String stateBeforeBorrow;

    private String stateAfterBorrow;

}
