package com.fileupload.multipleloginregistrationspringboot.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class Stocks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyName;
    private String stockPrice;
    private String image;
    private String stockUniqueId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(String stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStockUniqueId() {
        return stockUniqueId;
    }

    public void setStockUniqueId(String stockUniqueId) {
        this.stockUniqueId = stockUniqueId;
    }

    @Override
    public String toString() {
        return "Stocks{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", stockPrice='" + stockPrice + '\'' +
                ", image='" + image + '\'' +
                ", stockUniqueId='" + stockUniqueId + '\'' +
                '}';
    }
}
