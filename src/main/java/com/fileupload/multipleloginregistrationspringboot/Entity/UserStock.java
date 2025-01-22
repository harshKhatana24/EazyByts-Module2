package com.fileupload.multipleloginregistrationspringboot.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class UserStock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyName;
    private String stockPrice;
    private String stockQuantity;
    private String image;
    private String stockUniqueId;
    @ManyToOne
    private User user;

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
