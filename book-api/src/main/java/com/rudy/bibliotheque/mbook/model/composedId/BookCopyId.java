package com.rudy.bibliotheque.mbook.model.composedId;

import com.rudy.bibliotheque.mbook.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BookCopyId implements Serializable {

    @Column(nullable = false)
    private String code;

    @ManyToOne
    private Book book;

}
