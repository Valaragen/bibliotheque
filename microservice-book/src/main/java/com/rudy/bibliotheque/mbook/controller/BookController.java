package com.rudy.bibliotheque.mbook.controller;

import com.rudy.bibliotheque.mbook.DTO.BorrowDTO;
import com.rudy.bibliotheque.mbook.config.ApplicationPropertiesConfig;
import com.rudy.bibliotheque.mbook.model.Borrow;
import com.rudy.bibliotheque.mbook.service.BorrowService;
import com.rudy.bibliotheque.mbook.util.Constant;
import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller class for the api
 * It contains all the API endpoints
 */
@RestController
public class BookController {

    private BookService bookService;
    private BorrowService borrowService;
    private ApplicationPropertiesConfig applicationPropertiesConfig;

    @Autowired
    public BookController(BookService bookService, BorrowService borrowService, ApplicationPropertiesConfig applicationPropertiesConfig) {
        this.bookService = bookService;
        this.borrowService = borrowService;
        this.applicationPropertiesConfig = applicationPropertiesConfig;
    }

    /**
     * Get all the books from the database
     * @return List of books from the database
     */
    @GetMapping(Constant.BOOK_PATH)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
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

    /**
     * Get all the loans from the database
     * @return List of loans as a DTO that contains useful informations about the related book and user
     */
    @GetMapping(Constant.LOANS_PATH)
    public List<BorrowDTO> getAllLoans(){
        List<BorrowDTO> borrowDTOs = new ArrayList<>();

        long millis=System.currentTimeMillis();
        Date date = new Date(millis);

        List<Borrow> loans = borrowService.getAllLoans();
        for (Borrow borrow : loans) {
            borrowDTOs.add(borrowService.convertBorrowToDTO(borrow));
        }

        return borrowDTOs;
    }

    /**
     * Get all expired and non returned loans from the database
     * @return List of loans as a DTO that contains useful informations about the related book and user
     */
    @GetMapping(Constant.NONRETURNED_EXPIRED_LOANS_PATH)
    public List<BorrowDTO> getAllNonReturnedExpiredLoans(){
        List<BorrowDTO> borrowDTOs = new ArrayList<>();

        long millis=System.currentTimeMillis();
        Date date = new Date(millis);

        List<Borrow> loans = borrowService.getAllNonReturnedExpiredLoans(date);
        for (Borrow borrow : loans) {
            borrowDTOs.add(borrowService.convertBorrowToDTO(borrow));
        }

        return borrowDTOs;
    }

}
