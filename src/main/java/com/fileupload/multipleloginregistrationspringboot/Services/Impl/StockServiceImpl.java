package com.fileupload.multipleloginregistrationspringboot.Services.Impl;

import com.fileupload.multipleloginregistrationspringboot.Entity.Stocks;
import com.fileupload.multipleloginregistrationspringboot.Helper.ResourceNotFoundException;
import com.fileupload.multipleloginregistrationspringboot.Repository.StockRepository;
import com.fileupload.multipleloginregistrationspringboot.Services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Optional<Stocks> updateStock(Stocks stocks) {
        return Optional.empty();
    }

    @Override
    public void deleteStockByUniqueId(String uniqueStockId) {
        stockRepository.deleteStocksByStockUniqueId(uniqueStockId);
    }
}
