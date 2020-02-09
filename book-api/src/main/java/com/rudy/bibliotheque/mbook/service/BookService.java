package com.rudy.bibliotheque.mbook.service;

import com.rudy.bibliotheque.mbook.DTO.BookDTO;
import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.repository.BookRepository;
import org.springframework.beans.BeanUtils;
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

    /**
     * Find all Book Entity in the database
     * @return All Book entity found in the database as List
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Find a Book by id in the database
     * @param id id of the book to find
     * @return Book found in the database
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    /**
     * Save a new book in the database using a BookDTO
     * @param bookDTO DTO you want to use attributes from
     * @return The new book saved in database
     */
    public Book createBookFromDTO(BookDTO bookDTO) {
        Book book = convertDTOtoBook(bookDTO);
        return bookRepository.saveAndFlush(book);
    }

    /**
     * Convert a BookDTO to a Book object
     * @param bookDTO BookDTO you want to convert
     * @return new Book populated with the attributes of the BookDTO passed in parameter
     */
    public Book convertDTOtoBook(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        return book;
    }

    /**
     * Convert a Book to a BookDTO object
     * @param book Book you want to convert
     * @return new BookDTO populated with the attributes of the book passed in parameter
     */
    public BookDTO convertBookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        return bookDTO;
    }

    /**
     * Convert a Book List to a BookDTO List
     * @param books List<Book> you want to convert
     * @return new List<BookDTO> populated with the List<Book> passed in parameter
     */
    public List<BookDTO> convertBooksToDTOs(List<Book> books){
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            bookDTOs.add(convertBookToDTO(book));
        }
        return bookDTOs;
    }
}
