package com.fileupload.multipleloginregistrationspringboot.Annotations;

import com.fileupload.multipleloginregistrationspringboot.Repository.StockRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//implements ConstraintValidator<UniqueStockId, String>
public class UniqueStockIdValidator implements ConstraintValidator<UniqueStockId, String> {

    @Autowired
    private StockRepository stockRepository;
    //stockRepository mea check kiya ki stock with the id exists or not

    @Override
    public boolean isValid(String stockUniqueId, ConstraintValidatorContext context) {
        if (stockUniqueId == null || stockUniqueId.isEmpty()) {
            return true; // Allow null or empty; handle with other validations
        }
        return !stockRepository.existsByStockUniqueId(stockUniqueId);
    }
}
