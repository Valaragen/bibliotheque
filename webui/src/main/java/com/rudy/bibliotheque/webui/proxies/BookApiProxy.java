package com.rudy.bibliotheque.webui.proxies;

import com.rudy.bibliotheque.webui.dto.BookDTO;
import com.rudy.bibliotheque.webui.dto.BorrowDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "book-api")
public interface BookApiProxy {

    String AUTH_TOKEN = "Authorization";

    /**
     * Get all the books from the database
     * @return HashSet of books from the database
     */
    @GetMapping("/book-api/books")
    List<BookDTO> getAllBooks();


    @GetMapping("/book-api/loans")
    List<BorrowDTO> getAllLoans();

}
