package com.rudy.bibliotheque.webui.proxies;

import com.rudy.bibliotheque.webui.dto.BookDTO;
import com.rudy.bibliotheque.webui.dto.BorrowDTO;
import com.rudy.bibliotheque.webui.util.Constant;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "book-api")
@RequestMapping(Constant.BOOK_API_PATH)
public interface BookApiProxy {

    @GetMapping(Constant.BOOKS_PATH)
    List<BookDTO> getAllBooks();

    @GetMapping(Constant.LOANS_PATH)
    List<BorrowDTO> getAllLoans();

    @GetMapping(Constant.LOANS_PATH + Constant.USERS_PATH + "/current")
    List<BorrowDTO> getLoansByCurrentUser();

    @PostMapping(Constant.BOOKS_PATH)
    ResponseEntity<BookDTO> saveBookInDatabase(@RequestBody BookDTO bookDTO);

    @GetMapping(Constant.BOOKS_PATH + Constant.SLASH_ID_PATH)
    BookDTO getBookById(@PathVariable("id") Long id);

    @PutMapping(Constant.BOOKS_PATH + Constant.SLASH_ID_PATH)
    ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO);

    @DeleteMapping(Constant.BOOKS_PATH + Constant.SLASH_ID_PATH)
    ResponseEntity<BookDTO> deleteBook(@PathVariable("id") Long id);

    @PutMapping(Constant.LOANS_PATH + Constant.SLASH_ID_PATH + Constant.EXTEND_PATH)
    ResponseEntity<BorrowDTO> extendMyLoan(@PathVariable Long id);

}
