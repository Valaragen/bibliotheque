package com.rudy.bibliotheque.batch.proxy;

import com.rudy.bibliotheque.batch.DTO.BorrowDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "book-api")
public interface BookApiProxy {

    @GetMapping("/book-api/loans/nonReturnedExpiredLoans")
    List<BorrowDTO> getAllNonReturnedExpiredLoans();

}
