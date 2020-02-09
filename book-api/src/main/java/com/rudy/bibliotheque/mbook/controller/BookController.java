package com.rudy.bibliotheque.mbook.controller;

import com.rudy.bibliotheque.mbook.DTO.BookDTO;
import com.rudy.bibliotheque.mbook.config.ApplicationPropertiesConfig;
import com.rudy.bibliotheque.mbook.util.Constant;
import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BookDTO> getAllBooks(HttpServletRequest request) {
        List<Book> books = bookService.getAllBooks();
        return bookService.convertBooksToDTOs(books);
    }

    /**
     * Save a new book in the database
     * @return the new saved book
     */
    @PostMapping
    public BookDTO saveBookInDatabase(BookDTO bookDTO) {
        return bookService.convertBookToDTO(bookService.createBookFromDTO(bookDTO));
    }

    /**
     * Get the book with the id given in the path
     * @param id the id of the book
     * @return the book with the given id
     */
    @GetMapping(Constant.SLASH_ID)
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.convertBookToDTO(bookService.getBookById(id));
    }
}
