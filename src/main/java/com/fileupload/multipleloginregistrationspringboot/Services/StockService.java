package com.fileupload.multipleloginregistrationspringboot.Services;


import com.fileupload.multipleloginregistrationspringboot.Entity.Stocks;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface StockService {

    Optional<Stocks> updateStock(Stocks stocks);

    void deleteStockByUniqueId(String uniqueStockId);
}
