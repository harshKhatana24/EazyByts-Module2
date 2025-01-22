package com.fileupload.multipleloginregistrationspringboot.Form;

import com.fileupload.multipleloginregistrationspringboot.Annotations.UniqueStockId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BuyStockForm {

    @Size(min = 3, message = "Name must be greater than 3 letters")
    private String CompanyName;

    private String StockPrice;

    @NotBlank(message = "Stock Unique ID is required")
    @UniqueStockId // Custom validation annotation
    private String UniqueStockID;

    private String StockQuantity;

    public @Size(min = 3, message = "Name must be greater than 3 letters") String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(@Size(min = 3, message = "Name must be greater than 3 letters") String companyName) {
        CompanyName = companyName;
    }

    public String getStockPrice() {
        return StockPrice;
    }

    public void setStockPrice(String stockPrice) {
        StockPrice = stockPrice;
    }

    public @NotBlank(message = "Stock Unique ID is required") String getUniqueStockID() {
        return UniqueStockID;
    }

    public void setUniqueStockID(@NotBlank(message = "Stock Unique ID is required") String uniqueStockID) {
        UniqueStockID = uniqueStockID;
    }

    public String getStockQuantity() {
        return StockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        StockQuantity = stockQuantity;
    }
}
