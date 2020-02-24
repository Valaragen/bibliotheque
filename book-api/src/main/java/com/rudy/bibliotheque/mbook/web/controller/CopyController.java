package com.rudy.bibliotheque.mbook.web.controller;

import com.rudy.bibliotheque.mbook.config.ApplicationPropertiesConfig;
import com.rudy.bibliotheque.mbook.dto.CopyCreateDTO;
import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.model.Copy;
import com.rudy.bibliotheque.mbook.model.composedId.BookCopyId;
import com.rudy.bibliotheque.mbook.service.BookService;
import com.rudy.bibliotheque.mbook.service.CopyService;
import com.rudy.bibliotheque.mbook.util.Constant;
import com.rudy.bibliotheque.mbook.util.NullAwareBeanUtilsBean;
import com.rudy.bibliotheque.mbook.web.exception.CRUDIssueException;
import com.rudy.bibliotheque.mbook.web.exception.NotFoundException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Controller class for the api
 * It contains all the API endpoints
 */
@RestController
@RequestMapping(Constant.COPIES_PATH)
public class CopyController {

    private CopyService copyService;
    private BookService bookService;
    private ApplicationPropertiesConfig applicationPropertiesConfig;

    @Autowired
    public CopyController(CopyService copyService, BookService bookService, ApplicationPropertiesConfig applicationPropertiesConfig) {
        this.copyService = copyService;
        this.bookService = bookService;
        this.applicationPropertiesConfig = applicationPropertiesConfig;
    }

    /**
     * Get all the copies from the database
     *
     * @return List of books from the database
     */
    @GetMapping
    public List<Copy> getAllCopies() {
        return copyService.getAllCopies();
    }

    @GetMapping(Constant.BOOKS_PATH + Constant.SLASH_ID_PATH + Constant.COPY_CODE_PATH + Constant.SLASH_STRING_PATH)
    public Copy getCopyByComposedId(@PathVariable Long id, @PathVariable String string) {
        return copyService.getCopyByComposedId(string, id);
    }

    @GetMapping(Constant.BOOKS_PATH + Constant.SLASH_ID_PATH)
    public Copy getCopyByBookId(@PathVariable Long id) {
        return copyService.getCopyByBookId(id);
    }

    @PostMapping
    public ResponseEntity<Copy> saveCopyInDatabase(@RequestBody CopyCreateDTO copyCreateDTO) {
        Copy newCopy = new Copy();
        //Link book
        Book linkedBook = bookService.getBookById(copyCreateDTO.getBookId());
        if (linkedBook == null) {
            throw new NotFoundException("Can't find book with id " + copyCreateDTO.getBookId());
        }
        newCopy.setId(new BookCopyId(copyService.generateCode(), linkedBook));

        Book currentBook = newCopy.getId().getBook();
        newCopy.getId().getBook().setCopyNumber(currentBook.getCopyNumber() + 1);
        newCopy.getId().getBook().setAvailableCopyNumber(currentBook.getAvailableCopyNumber() + 1);

        //Bind fields
        newCopy.setStateAtPurchase(copyCreateDTO.getStateAtPurchase());
        newCopy.setCurrentState(copyCreateDTO.getStateAtPurchase());

        Copy newBook = copyService.saveCopy(newCopy);
        if (newBook == null) throw new CRUDIssueException("Can't create the copy");
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping(Constant.BOOKS_PATH + Constant.SLASH_ID_PATH + Constant.COPY_CODE_PATH + Constant.SLASH_STRING_PATH)
    public ResponseEntity<Copy> updateCopy(@PathVariable Long id, @PathVariable String string, @RequestBody Copy copy) throws InvocationTargetException, IllegalAccessException {
        Copy currentCopy = copyService.getCopyByComposedId(string, id);
        BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
        notNull.copyProperties(currentCopy, copy);

        Copy newCopy = copyService.saveCopy(currentCopy);
        if (newCopy == null) throw new CRUDIssueException("Can't update the copy");
        return new ResponseEntity<>(newCopy, HttpStatus.OK);
    }

}
