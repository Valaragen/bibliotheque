package com.rudy.bibliotheque.webui.proxies;

import com.rudy.bibliotheque.webui.beans.BookBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "microservice-book")
@RibbonClient(name = "microservice-book")
public interface MicroserviceBookProxy {

    /**
     * Get all the books from the database
     * @return HashSet of books from the database
     */
    @GetMapping("/books")
    public List<BookBean> getAllBooks();

}