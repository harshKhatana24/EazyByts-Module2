package com.fileupload.multipleloginregistrationspringboot.Repository;

import com.fileupload.multipleloginregistrationspringboot.Entity.User;
import com.fileupload.multipleloginregistrationspringboot.Entity.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStockRepo extends JpaRepository<UserStock, Long> {
    void deleteByStockUniqueId(String id);

    Optional<UserStock> findByStockUniqueIdAndUser(String id, User user);
}
