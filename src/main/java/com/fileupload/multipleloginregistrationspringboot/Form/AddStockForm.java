package com.fileupload.multipleloginregistrationspringboot.Form;

import com.fileupload.multipleloginregistrationspringboot.Annotations.UniqueStockId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public class AddStockForm {

    @Size(min = 3, message = "Name must be greater than 3 letters")
    private String name;

    private String price;

    @NotBlank(message = "Stock Unique ID is required")
    @UniqueStockId // Custom validation annotation
    private String stockUniqueId;

    public @Size(min = 3, message = "Name must be greater than 3 letters") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "Name must be greater than 3 letters") String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public @NotBlank(message = "Stock Unique ID is required") String getStockUniqueId() {
        return stockUniqueId;
    }

    public void setStockUniqueId(@NotBlank(message = "Stock Unique ID is required") String stockUniqueId) {
        this.stockUniqueId = stockUniqueId;
    }
}
