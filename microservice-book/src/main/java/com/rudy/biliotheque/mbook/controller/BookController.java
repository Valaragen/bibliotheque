package com.rudy.biliotheque.mbook.controller;

import com.rudy.biliotheque.mbook.config.ApplicationPropertiesConfig;
import com.rudy.biliotheque.mbook.model.Book;
import com.rudy.biliotheque.mbook.service.BookService;
import com.rudy.biliotheque.mbook.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
     * @return HashSet of books from the database
     */
    @GetMapping(Constant.BOOK_PATH)
    public List<Book> getAllBooks() {
        return bookService.getAllBook();
    }

    /**
     * Save a new book in database
     * @return the new saved book
     */
    @PostMapping(Constant.BOOK_PATH)
    public Book saveBookInDatabase() {
        //TODO implement logic
        return new Book();
    }

    /**
     * Get the book with the id given in the path
     * @param id the id of the book
     * @return the book with the given id
     */
    @GetMapping(Constant.BOOK_VIEW_PATH)
    public Book getBookView(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

}
