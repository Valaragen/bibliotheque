package com.rudy.bibliotheque.mbook.web.controller;

import com.rudy.bibliotheque.mbook.config.ApplicationPropertiesConfig;
import com.rudy.bibliotheque.mbook.web.exception.BookNotFoundException;
import com.rudy.bibliotheque.mbook.util.Constant;
import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.service.BookService;
import com.rudy.bibliotheque.mbook.web.exception.CantCreateBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Controller class for the api
 * It contains all the API endpoints
 */
@RestController
@RequestMapping(Constant.BOOKS_PATH)
public class BookController {

    private BookService bookService;
    private ApplicationPropertiesConfig applicationPropertiesConfig;

    @Autowired
    public BookController(BookService bookService, ApplicationPropertiesConfig applicationPropertiesConfig) {
        this.bookService = bookService;
        this.applicationPropertiesConfig = applicationPropertiesConfig;
    }

    /**
     * Get all the books from the database
     * @return List of books from the database
     */
    @GetMapping
    public List<Book> getAllBooks(HttpServletRequest request) {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) throw new BookNotFoundException();

        return books;
    }

    /**
     * Save a new book in the database
     * @return the new saved book
     */
    @PostMapping
    public ResponseEntity<Book> saveBookInDatabase(@RequestBody Book book) {
        Book newBook = bookService.createBook(book);
        if (newBook == null) throw new CantCreateBookException();
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    /**
     * Get the book with the id given in the path
     * @param id the id of the book
     * @return the book with the given id
     */
    @GetMapping(Constant.SLASH_ID)
    public Book getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if(book == null) throw new BookNotFoundException();
        return book;
    }
}
