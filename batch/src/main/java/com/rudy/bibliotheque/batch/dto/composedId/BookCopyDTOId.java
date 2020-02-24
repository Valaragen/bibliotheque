package com.rudy.bibliotheque.batch.dto.composedId;

import com.rudy.bibliotheque.batch.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCopyDTOId implements Serializable {
    private String code;
    private BookDTO book;
}
