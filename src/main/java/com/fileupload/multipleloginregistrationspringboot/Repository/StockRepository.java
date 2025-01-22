package com.fileupload.multipleloginregistrationspringboot.Repository;

import com.fileupload.multipleloginregistrationspringboot.Entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StockRepository extends JpaRepository<Stocks,Long> {


    boolean existsByStockUniqueId(String stockUniqueId);

    Optional<Stocks> findStocksByStockUniqueId(String stockUniqueId);


    void deleteStocksByStockUniqueId(String uniqueStockId);

}
