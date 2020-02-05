package com.rudy.bibliotheque.mbook.service;

import com.rudy.bibliotheque.mbook.DTO.BookDTO;
import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public BookDTO convertBookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setName(book.getName());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setReleaseDate(book.getReleaseDate());
        bookDTO.setCopyNumber(book.getCopyNumber());
        bookDTO.setAvailableCopyNumber(book.getAvailableCopyNumber());
        return bookDTO;
    }

    public List<BookDTO> convertBooksToDTOs(List<Book> books){
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            bookDTOs.add(convertBookToDTO(book));
        }
        return bookDTOs;
    }
}
